package pl.nauka;

import java.io.*;
import java.util.*;

public class FamilyTree {

    List<Person> persons;
    List<Person> personsOnTree;
    Person nodeOfTree;
    public static final int COUNT = 10;

    public FamilyTree(){
        persons = new ArrayList<Person>();
        personsOnTree = new ArrayList<>();
    }
    public void drawTreeInConsole(Person root, int space){
        if(root ==null)
            return;
        space += COUNT;

        drawTreeInConsole(root.mother,space);

       // System.out.println("\n");

        for (int i = COUNT;i<space;i++)
            System.out.print(" ");
        System.out.println(root.name + " ur."+root.yearOfBirth+  "\n ");


        drawTreeInConsole(root.father,space);
    }
    public void saveTreeToFile(Person root,int space){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("tree.txt"));
            drawInFile(writer,root,space);
            writer.close();
        }catch (IOException e){
            System.out.println("Sth went wrong");
        }
    }
    public static void drawInFile(BufferedWriter writer,Person root,int space) throws IOException {
        if(root==null)
            return;
        space+=COUNT;
        drawInFile(writer,root.mother,space);
        for (int i=COUNT;i<space;i++)
            writer.write(" ");
        writer.write(root.name+ " ur."+root.yearOfBirth + "\n\n");

        drawInFile(writer,root.father,space);
    }


    public void showInfoAboutParticularPerson(Person person){
        String motherInfo = informationAboutFamilyMember(person.mother,"mother");
        String fatherInfo = informationAboutFamilyMember(person.father, "father");
        String kidInfo = informationAboutFamilyMember(person.kid,"kid");
        String partnerInfo = informationAboutFamilyMember(person.partner,"partner");

        System.out.println("Name: "+ person.name +  ", YearOfBirth: "+ person.yearOfBirth
                +"\nMother information : "+motherInfo
                +"\nFather information: " + fatherInfo
                +"\nkid Information: "+ kidInfo
                +"\npartner information: "+ partnerInfo);
    }

    public String informationAboutFamilyMember(Person person,String member){
        if(person==null){
            return "no information about "+member;
        }else{
            return "Name: "+ person.name+", yearOfBirth: "+person.yearOfBirth;
        }
    }

    public Person findBersonById(int id){
        Person person;
        try {
           person= persons.get(id);
        }catch (IndexOutOfBoundsException e){
            person = null;
        }
        return person;

    }
    public Person findBersonOnTreeById(int id){
        Person person;
        try {
            person= personsOnTree.get(id);
        }catch (IndexOutOfBoundsException e){
            person = null;
        }
        return person;

    }
    public  void showTreeForTakenPerson(Scanner scanner) {
        if (this.personsOnTree.size() == 0) {
            System.out.println("There is no person on Family Tree");
        } else {
            generateForEachLoop(this.personsOnTree);
            String wybor = scanner.next();
            Person p1 = this.findBersonOnTreeById(Integer.parseInt(wybor));
            System.out.println("#######################################################################");
            System.out.println();
            this.drawTreeInConsole(p1,0);
            System.out.println();
            System.out.println("#######################################################################");
        }
    }

    public static void generateForEachLoop(List<Person> list){
        for (Person p : list
        ) {
            System.out.println("Nr." + list.indexOf(p)+ ": " + p.name + ", year of birth: " + p.yearOfBirth);
        }
    }

    public  void addPerson(Scanner scanner) {
        Person p;
        int yearOfBirth;
        System.out.println("Enter Name: ");
        String name = scanner.next();
        System.out.println("Enter Year of Birth");
        String year = scanner.next();
        while (true) {
            if (year.length() == 4 && (year.charAt(0) == '2' || year.charAt(0) == '1')) {
                yearOfBirth = Integer.parseInt(year);
                break;
            }
            System.out.println("Wrong year of Birth! Enter correct (4 digit start at '1' or '2'): ");
            year = scanner.next();

        }
        while (true) {
            System.out.println("Select sex:  m - male  / f - female");
            String sex = scanner.next();
            if(Objects.equals(sex, "m") || Objects.equals(sex, "f")){
                p = new Person(yearOfBirth,name,getCorrectSex(sex));
                this.persons.add(p);
                break;
            }
        }
    }
    public static Sex getCorrectSex(String sex){
        return Objects.equals(sex, "m") ?Sex.MAN:Sex.WOMAN;
    }

    public void selectPersonToDisplay(Scanner scanner){
        if(this.personsOnTree.size()==0){
            System.out.println("Tree is empty\n");

        }else {
            System.out.println("Select Id of Person to display");
            generateForEachLoop(this.personsOnTree);
            String wybor = scanner.next();
            Person p1 = this.findBersonOnTreeById(Integer.parseInt(wybor));
            if (p1 == null) {
                System.out.println("There is no person with that id number");
            } else {
                this.showInfoAboutParticularPerson(p1);
            }
        }
    }
    public  void showListOfPersonToSelectNodeOfFamilyTree(Scanner scanner) {
        if (this.personsOnTree.size() != 0) {
            System.out.println("Tree already hase node");
        } else if(this.persons.size()==0) {
            System.out.println("List is Empty");
        }
            else{
                System.out.println("Select Id of Person to Node");
                generateForEachLoop(this.persons);
                String wybor = scanner.next();
                Person p = this.findBersonById(Integer.parseInt(wybor));
                if (p == null) {
                    System.out.println("There is no person with that id number");
                } else {

                    if (this.personsOnTree.contains(p)) {
                        System.out.println("This person already belong to family Tree");
                    } else {
                        this.personsOnTree.add(p);
                        System.out.println(p.name + ", Birth:  " + p.yearOfBirth + " chosen to node of family Tree");
                        this.nodeOfTree = p;
                    }
                }
            }
        }


    public void addingRelation(Scanner scanner){
        if( this.nodeOfTree==null){
            System.out.println("There is no elements on family Tree");
            return;
        }
        boolean canBeParent=false;
        Person kid;
        Person parent;
        List<Person> personList = new ArrayList<>();
        for (Person p : this.personsOnTree){
            if(p.mother==null || p.father == null){
                personList.add(p);
            }
        }
        this.drawTreeInConsole(this.nodeOfTree,0);
        System.out.println("Select Person and add him a parent:");
        generateForEachLoop(personList);
        String wybor = scanner.next();
        try {
            kid = personList.get(Integer.parseInt(wybor));
        }catch (IndexOutOfBoundsException e){
            kid = null;
        }

        if(kid==null){
            System.out.println("There is no person with that id number");

        }
        else {
            List<Person> prospectiveParentList = new ArrayList<>();
            for (Person p : this.persons){
                if(!this.personsOnTree.contains(p)){
                    prospectiveParentList.add(p);
                }
            }

            if(prospectiveParentList.size()==0){
                System.out.println("There is no people to build relation. You need to add somebody using method 1");
                return;
            }
            System.out.println("Select prospective Parent: ");
            generateForEachLoop(prospectiveParentList);
            String choice2 = scanner.next();
            try {
                parent = prospectiveParentList.get(Integer.parseInt(choice2));
            }catch (IndexOutOfBoundsException e){
                parent = null;
            }
            if (parent==null){
                System.out.println("There is no person with that id number");
            }
            else
            {
                if (parent.sex == Sex.MAN){
                   canBeParent = kid.addFather(parent);
                   if(canBeParent)
                        this.personsOnTree.add(parent);
                    personList.clear();
                    prospectiveParentList.clear();
                }else {
                    canBeParent = kid.addMother(parent);
                    if(canBeParent)
                        this.personsOnTree.add(parent);
                    personList.clear();
                    prospectiveParentList.clear();
                }
            }

        }
    }
    public void clearFamilyTree(){
        for (Person p : this.personsOnTree
             ) {
            p.father= null;
            p.mother=null;
            p.kid = null;
            p.partner=null;
        }
        this.nodeOfTree = null;
        this.personsOnTree.clear();
        System.out.println("Tree is empty again");
    }

    public void saveCurrentFamilyMembersToFile(Scanner scanner){
        System.out.println("Enter name of file to write list of family tree's members: ");
        String fileName = scanner.next();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName+".txt"));
            for (Person p :this.personsOnTree
                 ) {
                String sex =p.sex.equals(Sex.MAN)?"m":"f";
                String person = p.yearOfBirth+";"+p.name+";"+sex;
                writer.write(person);
                writer.write("\n");
            }
            writer.close();
        }catch (IOException e){
            System.out.println("Sth went wrong");
        }

    }

    public FamilyTree readDataFromFile(Scanner scanner){
        //FamilyTree familyTree = new FamilyTree();
        if(this.personsOnTree.size()!=0 || this.nodeOfTree!=null){
            System.out.println("Family tree is not empty. Delete current tree to create new");
        }else{
            System.out.println("Enter file name: ");
            String filename = scanner.next();
            try (FileInputStream fileInputStream = new FileInputStream(filename+".txt");
                 BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream))){
                String line;
                while ((line = br.readLine()) != null) {
                    String[] persons = line.split(";");
                    if (getCorrectSex(persons[2]) == null) {
                        System.out.println("Incorrect data of person in file!");
                        System.out.println();
                    } else {
                        Person p1 = new Person(Integer.parseInt(persons[0]), persons[1], getCorrectSex(persons[2]));
                        this.persons.add(p1);
                    }

                }
            }catch (IOException e){
                System.out.println("Problem with reading data from fiel");
            }
        }
        return this;
    }


}
