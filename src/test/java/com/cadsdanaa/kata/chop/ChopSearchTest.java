package com.cadsdanaa.kata.chop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ChopSearchTest {

    @Test
    void shouldReturnIntegerGivenIntegerAndArrayOfIntegers() {
        Integer someInteger = 1;
        Integer[] someIntegerArray = new Integer[]{1, 2};

        Integer actual = ChopSearch.chop(someInteger, someIntegerArray);

        assertThat(actual).isInstanceOf(Integer.class);
    }

    @Test
    void shouldReturnPositionOfElementInSingleElementArray() {
        Integer expectedPosition = 0;
        Integer intToFind = 5;
        Integer[] arrayToSearch = new Integer[]{5};

        Integer actualPosition = ChopSearch.chop(intToFind, arrayToSearch);

        assertThat(actualPosition).isEqualTo(expectedPosition);
    }

    @ParameterizedTest
    @MethodSource
    void shouldReturnPositionOfElementInMultiElementSortedArray(Integer intToFind, Integer[] arrayToSearch, Integer expectedPosition) {
        Integer actualPosition = ChopSearch.chop(intToFind, arrayToSearch);

        assertThat(actualPosition).isEqualTo(expectedPosition);
    }

    private static Stream<Arguments> shouldReturnPositionOfElementInMultiElementSortedArray() {
        return Stream.of(
                Arguments.of(0, new Integer[]{0, 1}, 0),
                Arguments.of(1, new Integer[]{0, 1}, 1),
                Arguments.of(2, new Integer[]{0, 1, 2}, 2),
                Arguments.of(5, new Integer[]{0, 3, 5, 6, 9, 12, 31}, 2),
                Arguments.of(31, new Integer[]{0, 3, 5, 6, 9, 12, 31}, 6),
                Arguments.of(12, new Integer[]{-0, -3, -5, 6, 9, 12, 31}, 5),
                Arguments.of(-3, new Integer[]{-0, -3, -5, 6, 9, 12, 31}, 1)
        );
    }

    @ParameterizedTest
    @MethodSource
    void shouldReturnNegativeOneWhenIntegerNotPresent(Integer intToFind, Integer[] arrayToSearch) {
        Integer actualPosition = ChopSearch.chop(intToFind, arrayToSearch);

        assertThat(actualPosition).isEqualTo(-1);
    }

    private static Stream<Arguments> shouldReturnNegativeOneWhenIntegerNotPresent() {
        return Stream.of(
                Arguments.of(1, new Integer[]{0}),
                Arguments.of(2, new Integer[]{0, 1}),
                Arguments.of(-1, new Integer[]{0, 1}),
                Arguments.of(43, new Integer[]{0, 3, 5, 6, 9, 12, 31}),
                Arguments.of(3, new Integer[]{5, 6, 9, 12, 31})
        );
    }

}