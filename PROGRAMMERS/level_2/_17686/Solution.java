package PROGRAMMERS.level_2._17686;

import java.util.*;

public class Solution {
    public static class File {
        String originName;
        String head;
        int number;

        public File(String originName, String head, int number) {
            this.originName = originName;
            this.head = head;
            this.number = number;
        }
    }

    public String[] solution(String[] files) {
        List<File> list = new ArrayList<>();
        String[] answer = new String[files.length];

        for(String file : files) {
            String head = "";
            String number = "";
            int index = 0;

            while(!Character.isDigit(file.charAt(index))) head += file.charAt(index++);
            while(Character.isDigit(file.charAt(index))) {
                number += file.charAt(index++);
                if(index == file.length()) break;
            }
            list.add(new File(file, head.toUpperCase(), Integer.parseInt(number)));
        }

        Collections.sort(list, new Comparator<File>() {
            @Override
            public int compare(File f1, File f2) {
                int result = f1.head.compareTo(f2.head);

                if(result == 0) result = f1.number - f2.number;
                return result;
            }
        });

        for(int i=0; i<list.size(); i++) {
            answer[i] = list.get(i).originName;
        }
        return answer;
    }
}