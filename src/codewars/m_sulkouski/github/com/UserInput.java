package codewars.m_sulkouski.github.com;

public class UserInput {

    public static class TextInput {
        String value = "";

        public void add(char c) {
            this.value += c;
        }

        public String getValue() {
            return this.value;
        }
    }

    public static class NumericInput extends TextInput {
        @Override
        public void add(char c) {
            if(Character.isDigit(c)) {
                this.value += c;
            }
        }
    }

    public static void main(String[] args) {
        TextInput input = new NumericInput();
        input.add('1');
        input.add('a');
        input.add('0');
        System.out.println(input.getValue());
    }
}
