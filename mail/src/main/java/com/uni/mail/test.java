package com.uni.mail;
import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class test {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
//		String filename = "C:/test/00405509.htm";
		String filename = "D:/sts_projects/00405509.htm";
		
		Document doc = Jsoup.parse(new File(filename),"UTF-8");
		
		Elements meta2 = doc.getElementsByTag("meta");
		String str = meta2.toString();
		System.out.println("#####" + str.contains("ks_c_5601-1987"));
		
		
	}

}
