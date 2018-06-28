package com.ivan.newtechnologies.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;

public class Jackson {

    public static void main(String[] args) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String json = objectMapper.registerModule(new JavaTimeModule())
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(new Item("Book", 12));

        System.out.println(json);
    }

    static class Item {
        private String name;
        private int number;
        private LocalDateTime curDate;

        public Item(String name, int number) {
            this.name = name;
            this.number = number;
            curDate = LocalDateTime.now();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public LocalDateTime getCurDate() {
            return curDate;
        }

        public void setCurDate(LocalDateTime curDate) {
            this.curDate = curDate;
        }
    }
}
