//In this kata you have to write a Morse code decoder for wired electrical telegraph.
//        Electric telegraph is operated on a 2-wire line with a key that, when pressed, connects the wires together, which can be detected on a remote station.
//        The Morse code encodes every character being transmitted as a sequence of "dots" (short presses on the key) and "dashes" (long presses on the key).
//
//        When transmitting the Morse code, the international standard specifies that:
//
//        "Dot" – is 1 time unit long.
//        "Dash" – is 3 time units long.
//        Pause between dots and dashes in a character – is 1 time unit long.
//        Pause between characters inside a word – is 3 time units long.
//        Pause between words – is 7 time units long.
//        However, the standard does not specify how long that "time unit" is.
//        And in fact different operators would transmit at different speed. An amateur person may need a few seconds to transmit a single character,
//        a skilled professional can transmit 60 words per minute, and robotic transmitters may go way faster.
//
//        For this kata we assume the message receiving is performed automatically by the hardware that checks the line periodically,
//        and if the line is connected (the key at the remote station is down), 1 is recorded, and if the line is not connected (remote key is up),
//        0 is recorded. After the message is fully received, it gets to you for decoding as a string containing only symbols 0 and 1.

package morse_code_decoderii.codewars.m_sulkouski.github.com;


public class MorseCodeDecoder_II {
    public static void main(String[] args) {
        System.out.println(decodeMorse("1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111000000110011001111110000001111110011001100000011"));
    }


    private static String decodeBits(String rawBits) {
        int currentBitCounter;
        int index = 0;
        rawBits = trimBits(rawBits);
        char[] encryptedBitsValue = rawBits.toCharArray();
        char currentBitValue;
        int switchSpeed = detectSwitchSpeed(rawBits);
        StringBuilder processedBits = new StringBuilder();
        StringBuilder morseCode = new StringBuilder();

        while (index < encryptedBitsValue.length) {
            currentBitCounter = 0;
            currentBitValue = encryptedBitsValue[index];

            while (index < encryptedBitsValue.length && encryptedBitsValue[index] == currentBitValue) {
                currentBitCounter++;
                index++;
            }

            for (int i = 0; i < currentBitCounter / switchSpeed; i++) {
                processedBits.append(currentBitValue);
            }
        }

        index = 0;

        while (index < processedBits.length()) {
            currentBitCounter = 0;
            currentBitValue = processedBits.charAt(index);
            while (index < processedBits.length() && processedBits.charAt(index) == currentBitValue) {
                currentBitCounter++;
                index++;
            }
            if (currentBitValue == '1') {
                switch (currentBitCounter) {
                    case 1:
                        morseCode.append(".");
                        break;
                    case 3:
                        morseCode.append("-");
                        break;
                    default:
                }
            } else {
                switch (currentBitCounter) {
                    case 3:
                        morseCode.append(" ");
                        break;
                    case 7:
                        morseCode.append("   ");
                        break;
                    default:
                }
            }
        }

        return morseCode.toString();
    }

    private static String trimBits(String rawBits) {
        StringBuilder str = new StringBuilder(rawBits);
        int index = str.length() - 1;
        while (index >=0 && str.charAt(index) == '0') {
            str.deleteCharAt(index);
            index--;
        }

        if (str.length() == 0) {
            return "";
        }

        index = 0;
        while(str.charAt(index) == '0') {
            str.deleteCharAt(index);
        }

        return str.toString();
    }
    private static int detectSwitchSpeed(String bits) {                             //switch speed, i.e. bit ratio speed, equals the smallest number of 0's or 1's in a
        if (bits.length() <= 1) {                                                   //transmission. if >= 0, switch speed is 1
            return 1;
        }
        int index = 0;
        int endIndex = bits.length() - 1;
        int minBitCounter = endIndex;
        int currentBitCounter = 0;
        char currentValue;

        while (index < endIndex) {
            currentValue = bits.charAt(index);

            while (index <= endIndex && currentValue == bits.charAt(index)) {
                index++;
                currentBitCounter++;
            }

            if (currentBitCounter < minBitCounter) {
                minBitCounter = currentBitCounter;
            }
            currentBitCounter = 0;
        }
        return minBitCounter;
    }

    private static String decodeMorse(String codedText) {
        codedText = decodeBits(codedText);
        String[] codedPhrase = codedText.split("   ");
        String[] currentSymbols;
        StringBuilder decodedPhrase = new StringBuilder();

        for (String str : codedPhrase) {
            currentSymbols = str.split(" ");
            for (String s : currentSymbols) {
                if (!s.isEmpty()) {
                    decodedPhrase.append(MorseCode.get(s));
                }
            }
            decodedPhrase.append(" ");
        }

        return decodedPhrase.toString();
    }
}

class MorseCode {
    static String get(String symbol) {

        switch (symbol) {
            case "....":
                return "H";
            case ".":
                return "E";
            case "-.--":
                return "Y";
            case ".---":
                return "J";
            case "..-":
                return "U";
            case "-..":
                return "D";
            case "-":
                return "T";
            case"...":
                return "S";
            case"..":
                return "I";
            default:
                return "A";
        }
    }
}
