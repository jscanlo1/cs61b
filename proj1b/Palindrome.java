public class Palindrome {

    public Deque<Character> wordToDeque(String word){
        Deque<Character> wordDeque = new ArrayDeque<Character>();

        for(int i = 0; i < word.length(); i++){
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }


    public boolean isPalindrome(String word){
        if (word.length() == 0) {
            return false;
        }

        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindrome(wordDeque);
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        if (word.length() == 0) {
            return false;
        }

        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindrome(wordDeque, cc);
    }


    private boolean isPalindrome(Deque<Character> word){

        if(word.isEmpty() || word.size() == 1){
            return true;
        }

        Character first = word.removeFirst(), second = word.removeLast();
        if (first != second) {
            return false;
        } else {
            return isPalindrome(word);
        }

    }

    private boolean isPalindrome(Deque<Character> word, CharacterComparator cc){

        if(word.isEmpty() || word.size() == 1){
            return true;
        }

        Character first = word.removeFirst(), second = word.removeLast();

        if (cc.equalChars(first, second)) {
            return isPalindrome(word, cc);

        } else {
            return false;
        }

    }
}
