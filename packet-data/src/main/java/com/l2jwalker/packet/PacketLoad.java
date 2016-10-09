package com.l2jwalker.packet;

import com.l2jwalker.util.Util;
import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.exception.CustomChangeException;
import liquibase.exception.DatabaseException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.executor.Executor;
import liquibase.executor.ExecutorService;
import liquibase.resource.ResourceAccessor;
import liquibase.statement.core.RawSqlStatement;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PacketLoad implements CustomTaskChange {

    private final static String PACKET_FILE_NAME_SUFFIX = ".json";

    private final Logger log = Logger.getLogger(getClass());

    private String path;
    private String insertSql;
    private JSONParser jsonParser;
    private Set<JSONObject> packetsJson;
    private boolean okResourceParse = false;

    @Override
    public String getConfirmationMessage() {
        return "Packets successfully added to schema.";
    }

    @Override
    public void setUp() throws SetupException {
        jsonParser = new JSONParser();
        packetsJson = new HashSet<JSONObject>();
    }

    @Override
    public void setFileOpener(ResourceAccessor resourceAccessor) {
        okResourceParse = true;
        try {
            resourceAccessor.list(null, getPath(), true, false, true).stream()
                    .filter(path -> path.endsWith(PACKET_FILE_NAME_SUFFIX))
                    .forEach(path -> {
                                try {
                                    packetsJson.add((JSONObject) jsonParser.parse(IOUtils.toString(getClass().getResourceAsStream("/" + path))));
                                } catch (IOException | ParseException e) {
                                    okResourceParse = false;
                                    log.error(e);
                                }
                            }
                    );
        } catch (IOException e) {
            okResourceParse = false;
            log.error(e);
        }
    }

    @Override
    public ValidationErrors validate(Database var1) {
        return null;
    }

    @Override
    public void execute(Database database) throws CustomChangeException {
        if (!okResourceParse) {
            log.error("Resources do not parsed!");
            return;
        }
        Executor executor = ExecutorService.getInstance().getExecutor(database);
        packetsJson.forEach(json -> ((JSONObject) json.get("code")).entrySet().forEach(entry -> {
                    try {
                        executor.execute(new RawSqlStatement(getInsertSql()
                                .replaceFirst(":version", Version.valueOf((String) ((Map.Entry) entry).getKey()).name())
                                .replaceFirst(":code", Util.clearHex((String) ((Map.Entry) entry).getValue()))
                                .replaceFirst(":packetDirection", PacketDirection.valueOf((String) json.get("direction")).name())
                                .replaceFirst(":serverType", ServerType.valueOf((String) json.get("server")).name())
                                .replaceFirst(":name", (String) json.get("name"))
                                .replaceFirst(":body", json.get("body").toString())));
                    } catch (DatabaseException e) {
                        log.error(e);
                    }
                })
        );
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getInsertSql() {
        return insertSql;
    }

    public void setInsertSql(String insertSql) {
        this.insertSql = insertSql;
    }
}
