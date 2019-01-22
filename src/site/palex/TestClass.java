package site.palex;

public class TestClass {
    private String name = "Sasha";
    private int number = 777;

    //Нет гетера name!!!

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    //нет сеттера number

    public void pringData(){
        System.out.println(name + " " + number);
    }

    private void printFields(){
        System.out.println(name + " " + number);
    }

}
