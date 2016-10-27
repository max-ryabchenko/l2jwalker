package com.l2jwalker.character.etc;

import java.util.LinkedList;
import java.util.List;

public class Quests {

    private List<Quest> quests = new LinkedList<Quest>();

    public void addQuest(int _id, int _stage) {
        quests.add(new Quest(_id, _stage));
    }

    public List<Quest> getQuests() {
        return quests;
    }

    public class Quest {
        private int id;
        private int stage;

        public Quest(int _id, int _stage) {
            id = _id;
            stage = _stage;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStage() {
            return stage;
        }

        public void setStage(int _stage) {
            stage = _stage;
        }
    }

}
