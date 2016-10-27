package com.l2jwalker.character.knownlist;

import com.l2jwalker.character.etc.AbstractKnownList;
import com.l2jwalker.character.etc.Point;
import com.l2jwalker.character.gameobject.DroppedItem;
import com.l2jwalker.character.gameobject.GameObject;
import com.l2jwalker.character.gameobject.GameObjectType;
import com.l2jwalker.character.gameobject.NPC;
import javolution.util.FastList;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KnownList extends AbstractKnownList<GameObject> {

    private static final Logger LOG = Logger.getLogger(KnownList.class);

    protected Stream<GameObject> getStream(String name, GameObjectType type, long count) {
        Stream<GameObject> result = getKnownList().values().stream()
                .filter(object -> type == object.getGameObjectType())
                .filter(player -> null == name || name.equalsIgnoreCase(player.getName()));
        if (0 < count) {
            result.limit(count);
        }
        return result;
    }

    public synchronized List<GameObject> get(GameObjectType type) {
        return getStream(null, type, 0).collect(Collectors.toList());
    }

    public synchronized List<NPC> getMobs() {
        return getStream(null, GameObjectType.MOB, 0).map(NPC.class::cast).collect(Collectors.toList());
    }

    public synchronized List<DroppedItem> getItems() {
        return getStream(null, GameObjectType.Item, 0).map(DroppedItem.class::cast).collect(Collectors.toList());
    }

    public synchronized List<NPC> getNpcs() {
        return getStream(null, GameObjectType.NPC, 0).map(NPC.class::cast).collect(Collectors.toList());
    }

    public synchronized List<DroppedItem> getNearlyItems(Point point, long count) {
        if (null == point) {
            return FastList.newInstance();
        }
        return getStream(null, GameObjectType.Item, count)
                .sorted((item1, item2) -> Integer.compare(point.dist2D(item1.getPoint()), point.dist2D(item2.getPoint())))
                .limit(count)
                .map(DroppedItem.class::cast)
                .collect(Collectors.toList());
    }

    public synchronized List<NPC> getNearlyPlayers(Point point, long count) {
        if (null == point) {
            return FastList.newInstance();
        }
        return getStream(null, GameObjectType.Player, count)
                .sorted((player1, player2) -> Integer.compare(point.dist2D(player1.getPoint()), point.dist2D(player2.getPoint())))
                .limit(count)
                .map(NPC.class::cast)
                .collect(Collectors.toList());
    }

    public synchronized GameObject getPlayer(String name) throws NoSuchElementException {
        return getStream(name, GameObjectType.Player, 1).findFirst().get();
    }

    public synchronized GameObject getItem(String name) throws NoSuchElementException {
        return getStream(name, GameObjectType.Item, 1).findFirst().get();
    }

}
