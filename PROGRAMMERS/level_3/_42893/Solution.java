package PROGRAMMERS.level_3._42893;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static class Site implements Comparable<Site> {
        int index;
        int baseScore;
        float linkScore;
        String address;
        List<String> externalLinks;

        @Override
        public int compareTo(Site site) {
            float a = (float) this.baseScore + this.linkScore;
            float b = (float) site.baseScore + site.linkScore;

            return Float.compare(b, a);
        }
    }

    public static int countBaseScore(String word, String page) {
        int ret = 0;
        int idx = page.indexOf(word);
        while(idx != -1) {
            char pre = page.charAt(idx - 1);
            char post = page.charAt(idx + word.length());
            if(!Character.isLowerCase(pre) && !Character.isLowerCase(post)) {
                ret++;
            }
            idx = page.indexOf(word, idx + 1);
        }

        return ret;
    }

    public static String findAddress(String page) {
        String ret = "";
        Pattern pattern = Pattern.compile("<meta property=\"og:url\" content=\"https://(.+?)\"/>");
        Matcher matcher = pattern.matcher(page);
        while(matcher.find()) {
            ret = matcher.group(1);
        }

        return ret;
    }

    public static List<String> findExternalLinks(String page) {
        List<String> ret = new ArrayList<>();
        Pattern pattern = Pattern.compile("<a href=\"https://(.+?)\">");
        Matcher matcher = pattern.matcher(page);
        while(matcher.find()) {
            ret.add(matcher.group(1));
        }
        return ret;
    }

    public static int solution(String word, String[] pages) {
        Map<String,Site> sites = new HashMap<>();
        int index = 0;

        word = word.toLowerCase();
        for(String page : pages) {
            Site site = new Site();
            site.index = index++;
            site.address = findAddress(page);
            site.baseScore = countBaseScore(word, page.toLowerCase());
            site.externalLinks = findExternalLinks(page);
            sites.put(site.address, site);
        }
        for(String key : sites.keySet()) {
            for(String link : sites.get(key).externalLinks) {
                if(sites.containsKey(link)) {
                    sites.get(link).linkScore += sites.get(key).baseScore / (float) sites.get(key).externalLinks.size();
                }
            }
        }

        List<Site> list = new ArrayList<>(sites.values());
        Collections.sort(list);

        return list.get(0).index;
    }

    public static void main(String[] args) {
//        String word = "blind";
//        String[] pages = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
//                "<head>\n" +
//                "  <meta charset=\"utf-8\">\n" +
//                "  <meta property=\"og:url\" content=\"https://a.com\"/>\n" +
//                "</head>  \n" +
//                "<body>\n" +
//                "Blind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n" +
//                "<a href=\"https://b.com\"> Link to b </a>\n" +
//                "</body>\n" +
//                "</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
//                "<head>\n" +
//                "  <meta charset=\"utf-8\">\n" +
//                "  <meta property=\"og:url\" content=\"https://b.com\"/>\n" +
//                "</head>  \n" +
//                "<body>\n" +
//                "Suspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n" +
//                "<a href=\"https://a.com\"> Link to a </a>\n" +
//                "blind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n" +
//                "<a href=\"https://c.com\"> Link to c </a>\n" +
//                "</body>\n" +
//                "</html>", "</html>\n" +
//                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
//                "<head>\n" +
//                "  <meta charset=\"utf-8\">\n" +
//                "  <meta property=\"og:url\" content=\"https://c.com\"/>\n" +
//                "</head>  \n" +
//                "<body>\n" +
//                "Ut condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n" +
//                "<a href=\"https://a.com\"> Link to a </a>\n" +
//                "</body>\n" +
//                "</html>"};
//
//        System.out.println(solution(word, pages));


        String word = "Muzi";
        String[] pages = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n" +
                "</head>  \n" +
                "<body>\n" +
                "<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n" +
                "\n" +
                "</body>\n" +
                "</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n" +
                "</head>  \n" +
                "<body>\n" +
                "con%    muzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n" +
                "\n" +
                "    ^\n" +
                "</body>\n" +
                "</html>"};
        System.out.println(solution(word, pages));
    }
}
