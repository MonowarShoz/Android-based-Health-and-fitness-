package com.shoz.monowar.fitnessandhealth;

public class SampleFood {

        private String sFname, sFser, sFcalories;



        public SampleFood(String sftitle, String sfcal, String sfsev) {
            this.sFname = sftitle;
            this.sFcalories = sfcal;
            this.sFser = sfsev;
        }

        public String getTitle() {
            return sFname;
        }

        public void setTitle(String name) {
            this.sFname = name;
        }

        public String getYear() {
            return sFser;
        }

        public void setYear(String year) {
            this.sFser = year;
        }

        public String getGenre() {
            return sFcalories;
        }

        public void setGenre(String genre) {
            this.sFcalories = genre;
        }


}

