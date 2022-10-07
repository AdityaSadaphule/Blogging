package co.mushu.blogging.utility;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConditionalUtility {
    public String isPasswordValid(String password){
        if(password.length() < 8) return "password length should be at least of 8 character";
        boolean digit = false;
        boolean alpha = false;
        boolean capital = false;
        boolean special = false;
        boolean space = false;
        for(char ch : password.toCharArray()){
            if(Character.isUpperCase(ch)) capital = true;
            else if(Character.isAlphabetic(ch)) alpha = true;
            else if(Character.isDigit(ch)) digit = true;
            else if(Character.isSpaceChar(ch)) space = true;
            else special = true;
        }
        if(!digit || !alpha || !capital || !special){
            List<String> list = new ArrayList<>();
            if(!digit){
                list.add("Digit");
            }
            if(!alpha){
                list.add("Alphabet");
            }
            if(!capital){
                list.add("Uppercase");
            }
            if(!special){
                list.add("Special Character(e.g %,!,@,$)");
            }
            StringBuilder sb = new StringBuilder("password should contain at least 1 ");
            boolean first = false;
            for(String cmt : list){
                if(!first) sb.append(cmt);
                else sb.append(", "+cmt);
                first = true;
            }
            sb.append(". ");
            if(space){
                sb.append("password should not contain space.");
            }
            return new String(sb);
        }else if(space){
            return "password should not contain space.";
        }
        return "valid";
    }
}
