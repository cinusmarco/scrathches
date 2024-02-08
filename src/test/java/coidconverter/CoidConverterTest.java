/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2023.
 */

package coidconverter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CoidConverterTest {

  @ParameterizedTest
  @MethodSource(value = "generator")
  void coidGenerator_returnsCorrectResults(
      String dossierNummer, String positionNummer, int fp, String expectedCoid) {
    CoidConverter systemUnderTest = new CoidConverter();
    String actualCoid = systemUnderTest.convert(dossierNummer, positionNummer, fp);

    assertThat(actualCoid).isEqualTo(expectedCoid);
  }

  @ParameterizedTest
  @MethodSource(value = "coid_validity_test_provider")
  void isCoidValid_returnCorrectAnswer(String coid, boolean expectedResult) {
    CoidConverter systemUnderTest = new CoidConverter();

    boolean actualResult = systemUnderTest.isValidCoid(coid);
    assertThat(actualResult).isEqualTo(expectedResult);
  }

  private static Stream<Arguments> generator() {
    return Stream.of(
        Arguments.of("123456", "001", 2022, "2022-C-222O1"),
        Arguments.of("063456", "001", 2022, "2022-B-222O1"),
        Arguments.of("003456", "001", 2022, "2022-A-222O1"),
        Arguments.of("120000", "001", 2022, "2022-C-00001"),
        Arguments.of("060000", "001", 2022, "2022-B-00001"),
        Arguments.of("000001", "001", 2022, "2022-A-000RT"),
        Arguments.of("00999", "0001", 2024, "2024-A-5Y4C1"),
        Arguments.of("19679", "001", 2023, "2023-A-BPSEX"),
        Arguments.of("65029", "017", 2022, "2022-B-2ZSEX"),
        Arguments.of("128015", "001", 2022, "2022-C-4RSEX"),
        Arguments.of("27", "0105", 2024, "2024-A-05SEX"),
        Arguments.of("65778", "020", 2021, "2021-B-3FUCK"));
  }

  private static Stream<Arguments> coid_validity_test_provider() {
    return Stream.of(
        Arguments.of("2022-C-222O1", true),
        Arguments.of("2022-B-222O1", true),
        Arguments.of("2022-A-222O1", true),
        Arguments.of("2022-C-00001", true),
        Arguments.of("2022-B-00001", true),
        Arguments.of("2022-A-000RT", true),
        Arguments.of("2024-A-5Y4C1", true),
        Arguments.of("2023-A-BPSEX", true),
        Arguments.of("2022-B-2ZSEX", true),
        Arguments.of("2022-C-4RSEX", true),
        Arguments.of("2024-A-05SEX", true),
        Arguments.of("2021-B-3FUCK", true),
        Arguments.of("2021-T-3FUCK", true),
        Arguments.of("2021-N-3FUCK", true),
        Arguments.of("2024-D-5Y4C1", false),
        Arguments.of("202A-A-BPSEX", false),
        Arguments.of("202-B-2ZSEX", false),
        Arguments.of("2022 C-4RSEX", false),
        Arguments.of("2022 T-4RSEX", false),
        Arguments.of("2024-A-05SE", false),
        Arguments.of("2024-A0005SE", false),
        Arguments.of("2021-B-3FUCKY", false));
  }
}
