package com.cadsdanaa.kata.chop;

public class ChopSearch {

    public static Integer chop(Integer intToFind, Integer[] arrayToSearch) {
        return search(intToFind, arrayToSearch, 0, arrayToSearch.length-1);
    }

    private static Integer search(Integer intToFind, Integer[] arrayToSearch, int lowerBound, int upperBound) {
        int position = (upperBound+lowerBound)/2;
        if(arrayToSearch[position].equals(intToFind)) {
            return position;
        }
        if(lowerBound == upperBound) {
            return -1;
        }
        if(arrayToSearch[position] < intToFind) {
            position = position == lowerBound ? position+1 : position;
            return search(intToFind, arrayToSearch, position, upperBound);
        } else {
            position = position == upperBound ? position-1 : position;
            return search(intToFind, arrayToSearch, lowerBound, position);
        }
    }

}
