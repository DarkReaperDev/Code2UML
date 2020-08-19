package com.TextUML.UMLObjects;

import java.util.ArrayList;
import java.util.List;

public class UMLClassObject implements UMLObject {


    List<UMLMethodObject> umlMethods = new ArrayList<>();
    List<UMLMemberObject> umlMembers = new ArrayList<>();
    String name;

    public UMLClassObject(String name){
        this.name = name;
    }

    public void Add(UMLObject object){
        if(object.getClass() == UMLMemberObject.class){
            umlMembers.add((UMLMemberObject) object);
        }
        else if(object.getClass() == UMLMethodObject.class){
            umlMethods.add((UMLMethodObject) object);
        }
    }

    public String getName() {
        return name;
    }

    public UMLMemberObject[] getUmlMembers() {
        return umlMembers.toArray(new UMLMemberObject[]{});
    }

    public UMLMethodObject[] getUmlMethods() {
        return umlMethods.toArray(new UMLMethodObject[]{});
    }

    public UMLObject[] getUMLMembersAndMethods(){
        return MergeUMLObjectArrays(getUmlMembers(), getUmlMethods());
    }

    @Override
    public String getFullString() {
        return name;
    }

    private static UMLObject[] MergeUMLObjectArrays(UMLObject[] firstArray, UMLObject[] secondArray){
        int firstArrayLength = firstArray.length;
        int secondArrayLength = secondArray.length;
        UMLObject[] resultArray = new UMLObject[firstArrayLength + secondArrayLength];

        System.arraycopy(firstArray, 0, resultArray, 0, firstArrayLength);
        System.arraycopy(secondArray, 0, resultArray, firstArrayLength, secondArrayLength);

        return  resultArray;
    }

}
