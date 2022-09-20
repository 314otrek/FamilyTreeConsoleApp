package pl.nauka;

import java.util.Date;

public class Person {

    int yearOfBirth;
    String name;
    Sex sex;
    Person mother;
    Person father;
    Person kid;
    Person partner;
    
    public Person(int yearOfBirth, String name, Sex sex){
        this.yearOfBirth = yearOfBirth;
        this.name = name;
        this.sex = sex;
        this.mother =null;
        this.father = null;
        this.partner = null;
        this.kid = null;
    }

    public boolean addFather(Person person){
        if(person.sex.equals(Sex.MAN) &&  ((this.yearOfBirth-person.yearOfBirth >18)&& ( this.yearOfBirth- person.yearOfBirth)<40) && this.father == null){
            this.father = person;
            this.father.kid= this;
            if(this.mother!=null){
                this.mother.partner = this.father;
                this.father.partner = this.mother;
            }
            System.out.println(person.name + " is father of " + this.name);
            System.out.println();
            return true;
        }
        else{
            System.out.println("This person cant be father for " + this.name);
            System.out.println();
            return  false;
        }
    }
    public boolean addMother(Person person){
        if(person.sex.equals(Sex.WOMAN) && ((this.yearOfBirth-person.yearOfBirth >18)&& ( this.yearOfBirth- person.yearOfBirth)<40)&& this.mother == null){
            this.mother = person;
            this.mother.kid = this;
            if(this.father!=null){
                this.father.partner = this.mother;
                this.mother.partner = this.father;
            }
            System.out.println(person.name + " is mather of " + this.name);
            System.out.println();
            return true;
        }
        else{
            System.out.println("This person cant be mother for "+this.name);
            System.out.println();
            return false;
        }

    }







}
