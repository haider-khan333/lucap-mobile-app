package com.fyp.lucapp.Helper;

public enum Enums {
    ACTIVE {
        @Override
        public String toString() {
            return "Active";
        }
    },
    CANCELLED {
        @Override
        public String toString() {
            return "Cancelled";
        }
    },
    COMPLETED {
        @Override
        public String toString() {
            return "Completed";
        }
    },

    PENDING {
        @Override
        public String toString() {
            return "Pending";
        }
    },
    Ok,
    Error,
    Success,
    Login,
    Register


}
