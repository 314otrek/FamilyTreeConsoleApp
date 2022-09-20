package pl.nauka;

import com.sun.security.jgss.GSSUtil;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Main {
   public static final String FILE_PATH= "file.txt";


    public static void main(String[] args) {

        Person p1 = new Person(1985,"Tomasz",Sex.MAN);
        Person p2 = new Person(1981,"Natalia",Sex.WOMAN);
        Person p3 = new Person(2003,"Karol",Sex.MAN);
        Person p4 = new Person(1962,"Marysia",Sex.WOMAN);
        Person p5 = new Person(1962,"Barman",Sex.MAN);
        Person p6 = new Person(1962,"Teodora",Sex.WOMAN);
        Person p7 = new Person(1962,"Barlomiej",Sex.MAN);


//        p3.addFather(p1);
//        p3.addMother(p2);
////
//        p1.addMother(p5);
//        p1.addMother(p4);
//        p1.addFather(p5);
//        p2.addMother(p6);
//        p2.addFather(p7);



//        FamilyTree familyTree = new FamilyTree();
//        familyTree.persons.add(p1);
//        familyTree.persons.add(p2);
//        familyTree.persons.add(p3);
//        familyTree.persons.add(p4);
//        familyTree.persons.add(p5);


       // familyTree.showInfoAboutParticularPerson(p1);
        //System.out.println();
        //familyTree.showInfoAboutParticularPerson(p2);
        //System.out.println();
       // familyTree.showInfoAboutParticularPerson(p7);

        //System.out.println();
        //System.out.println();
        FamilyTree familyTree=new FamilyTree();
        Scanner scanner = new Scanner(System.in);

            while (true) {
                   //try {
//                    if(familyTree==null){
//                    familyTree =  readFile();
//                        if(familyTree.persons.size()!=0)
//                        System.out.println("Correct read of people from file");
//                    }
                System.out.println("1 - add new Person");
                System.out.println("2 - select Person to Display");
                System.out.println("3 - show tree for given person");
                System.out.println("4 - Add relation between persons");
                System.out.println("5 - Select Person to create Family Tree");
                System.out.println("6 - Save current  Family Tree to file");
                System.out.println("7 - clear Family Tree");
                System.out.println("8 - Save current Family Tree Members to file");
                System.out.println("9 - Read data From file");
                System.out.println("0 - Exit App");
                final String next = scanner.next();
                if (next.equals("1")) {
                    familyTree.addPerson(scanner);
                }
                if (next.equals("2")) {
                    familyTree.selectPersonToDisplay(scanner);
                }
                if (next.equals("3")) {
                   familyTree.showTreeForTakenPerson(scanner);
                }
                if (next.equals("4")) {
                    familyTree.addingRelation(scanner);

                }
                if (next.equals("5")) {
                    familyTree.showListOfPersonToSelectNodeOfFamilyTree(scanner);

                }
                if(next.equals("6")){
                    familyTree.saveTreeToFile(familyTree.nodeOfTree,0);
                }
                if(next.equals("7")){
                    familyTree.clearFamilyTree();
                }
                if(next.equals("8")){
                    familyTree.saveCurrentFamilyMembersToFile(scanner);
                }
                if(next.equals("9")){
                    familyTree  = familyTree.readDataFromFile(scanner);
                }
                if (next.equals("0")) {
                    break;
                }

//                } catch (IOException e){
//                    System.out.println("Problem with reading file. Check if name and path is correct");
//                }
            }
            scanner.close();

    }
    public static FamilyTree readFile()throws IOException {
        FamilyTree familyTree = new FamilyTree();

            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;

            while ((line = br.readLine()) != null) {
                String[] persons = line.split(";");
                if (getCorrectSex(persons[2]) == null) {
                    System.out.println("Incorrect data of person in file!");
                    System.out.println();
                } else {
                    Person p1 = new Person(Integer.parseInt(persons[0]), persons[1], getCorrectSex(persons[2]));
                    familyTree.persons.add(p1);
                }

            }
            fileInputStream.close();
            br.close();
        return familyTree;
    }

    public static Sex getCorrectSex(String sex){
        if(sex.equals("m"))
            return Sex.MAN;
        else if(sex.equals("f"))
            return Sex.WOMAN;
        return null;
    }

}
