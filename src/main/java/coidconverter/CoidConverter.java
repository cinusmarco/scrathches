/*
 * Copyright (C) Schweizerische Bundesbahnen SBB, 2023.
 */

package coidconverter;

import org.apache.commons.lang3.StringUtils;

public class CoidConverter {

  private static final int BUCKET_SIZE = 60000000;
  public static final String PADDING_CHAR = "0";

  public String convert(String dossierNummer, String positionNummer, int fp) {

    /*
    seq-number is 5 digits dossiernumber + 4 digits positionnumber if FP > 2023
    seq-number is 6 digits dossiernumber + 3 digits positionnumber else
     */
    int positionNumberPadding = 9 - (fp > 2023 ? 5 : 6);

    String paddedPositionNummer =
        StringUtils.leftPad(positionNummer, positionNumberPadding, PADDING_CHAR);

    String seqNumber = dossierNummer + paddedPositionNummer;

    int seqNumberBase10 = Integer.parseInt(seqNumber);

    int seqNumberShifted = seqNumberBase10 % BUCKET_SIZE;
    char bucket = (char) ('A' + seqNumberBase10 / BUCKET_SIZE);

    String base36Padded =
        StringUtils.leftPad(Integer.toString(seqNumberShifted, 36), 5, PADDING_CHAR);

    return String.format("%d-%s-%s", fp, bucket, base36Padded.toUpperCase());
  }

  public boolean isValidCoid(String coid) {
    String regexPattern = "^\\d{4}-[A-C,NT]-\\w{5}$";
    return coid.matches(regexPattern);
  }
}
