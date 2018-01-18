package com.auto.myte.common;

public enum CategoryEnum {
	TAXI("1"),
	MEALS("2");

    private String context;
    private String getContext(){
   	 return this.context;
    }
    private CategoryEnum(String context){
   	 this.context = context;
    }
    

    public static void main(String[] args){
   	 for(CategoryEnum name :CategoryEnum.values()){
   		 System.out.println(name+" : "+name.getContext());
   		 System.out.println(name+" : "+CategoryEnum.TAXI);
   	 }
   	 System.out.println(CategoryEnum.TAXI.getDeclaringClass());
    }
}
