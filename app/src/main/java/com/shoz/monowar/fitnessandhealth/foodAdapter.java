package com.shoz.monowar.fitnessandhealth;




public class foodAdapter {



    public int id;
    public String foodtitle;


    public String foodserv;

    public String foodcal;

    public foodAdapter(){

    }



        public foodAdapter(int id,String ftitle, String fcal, String fser) {
            this.id = id;
            this.foodtitle = ftitle;
            this.foodcal = fcal;
            this.foodserv = fser;
        }


       public int getId(){return id;}
       public void setId(int id){this.id=id;}
        public String getFoodTitle() {
            return foodtitle;
        }
        public void setTitle(String name) {
            this.foodtitle = name;
        }

        public String getcal() {
            return foodcal;
        }

        public void setCal(String cal) {
            this.foodcal = cal;
        }

        public String getSer() {
            return foodserv;
        }

        public void setSer(String ser) {
            this.foodserv = ser;
        }
    }

