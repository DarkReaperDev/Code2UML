package com.TextUML.UMLObjects;

import com.TextUML.Utilities.Strings.MultilineString;

import java.util.ArrayList;
import java.util.List;

public class UMLClassObject implements UMLObject {
    private String name;
    private int ID;

    private List<UMLMethodObject> umlMethods = new ArrayList<>();
    private List<UMLMemberObject> umlMembers = new ArrayList<>();
    private List<Integer> umlSubclassesIDs = new ArrayList<>();

    public String umlParentName = "";
    public String umlInterfaceName = "";

    boolean isInterface = false;

    public UMLClassObject(String name, int ID){
        this.name = name;
        this.ID = ID;
    }

    public void Add(UMLObject object){
        if(object.getClass() == UMLMemberObject.class){
            umlMembers.add((UMLMemberObject) object);
        }
        else if(object.getClass() == UMLMethodObject.class){
            umlMethods.add((UMLMethodObject) object);
        }
    }

    @Override
    public MultilineString getFullString() {
        if(isInterface){
            return new MultilineString(new String[]{"<<interface>>", name});
        }
        return new MultilineString(new String[]{name});
    }

    private static UMLObject[] MergeUMLObjectArrays(UMLObject[] firstArray, UMLObject[] secondArray){
        int firstArrayLength = firstArray.length;
        int secondArrayLength = secondArray.length;
        UMLObject[] resultArray = new UMLObject[firstArrayLength + secondArrayLength];

        System.arraycopy(firstArray, 0, resultArray, 0, firstArrayLength);
        System.arraycopy(secondArray, 0, resultArray, firstArrayLength, secondArrayLength);

        return  resultArray;
    }

    public void AddSubClass(int ID){
        umlSubclassesIDs.add(ID);
    }

    public int[] GetUmlSubclassesIDs() {
        int[] ret = new int[umlSubclassesIDs.size()];
        for(int i = 0; i < umlSubclassesIDs.size(); i++){
            ret[i] = umlSubclassesIDs.get(i);
        }
        return ret;
    }

    public boolean IsInterface() {
        return isInterface;
    }

    public void SetInterface(boolean anInterface) {
        isInterface = anInterface;
    }

    public String GetName() {
        return name;
    }

    public int GetID(){
        return ID;
    }

    public UMLMemberObject[] GetUmlMembers() {
        return umlMembers.toArray(new UMLMemberObject[]{});
    }

    public UMLMethodObject[] GetUmlMethods() {
        return umlMethods.toArray(new UMLMethodObject[]{});
    }

    public UMLObject[] GetUMLMembersAndMethods(){
        return MergeUMLObjectArrays(GetUmlMembers(), GetUmlMethods());
    }
}
