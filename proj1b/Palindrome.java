public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque result = new LinkedListDeque();
        for(int i = 0;i < word.length();i++){
            result.addLast(word.charAt(i));
        }
        return result;
    }
    public boolean isPalindrome(String word){
        if(word == ""){
            return true;
        }
        Deque d = wordToDeque(word);
        Character front,rour;
        while(!d.isEmpty()){
            front = (Character)d.removeFirst();
            rour = (Character)d.removeLast();
            if(front == null || rour == null){
                break;
            }
            if(front != rour){
                return false;
            }
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc){
        if(word == ""){
            return true;
        }
        Deque d = wordToDeque(word);
        Character front,rour;
        while(!d.isEmpty()){
            front = (Character)d.removeFirst();
            rour = (Character)d.removeLast();
            if(front == null || rour == null){
                break;
            }
            if(cc.equalChars(front,rour) == false){
                return false;
            }
        }
        return true;
    }
}
