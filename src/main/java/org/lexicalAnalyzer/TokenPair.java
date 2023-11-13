package org.lexicalAnalyzer;

public class TokenPair {

        private String key;
        private String value;

        public TokenPair(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return this.key;
        }

        public String getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return  "Token -> " + key + "; " +
                    "lexeme -> " + value;
        }
}
