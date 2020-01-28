package com.uni.mail;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.spel.ast.Indexer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;

/**
 * Handles requests for the application home page.
 */



@Controller
@RequestMapping(value="/")
public class HomeController {
		
	Boolean STRINGTEST = false;
	/*
	public File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		
		
		//jsoup
//		String filename = convFile.getAbsolutePath();
		/**
		 * 경로 mail
		 *
		String filename = "D:/sts_projects/mail/jhkang.email/jhkang.email/" +file.getOriginalFilename();
		
		Document doc = Jsoup.parse(new File(filename),"UTF-8");
		
		Elements meta2 = doc.getElementsByTag("meta");
		String str = meta2.toString();
		STRINGTEST = str.contains("ks_c_5601-1987");		
		
		
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}*/
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
	public List<String> fileList = new ArrayList<String>();
	
	
	//파일 읽기
	public String fileRead(int cnt) {
		String str = new String();
		try{
			//String str = new String();
            //파일 객체 생성
			File readfile = new File(fileList.get(cnt));
            //입력 스트림 생성
			BufferedReader file_reader = null;
			
			//jsoup 사용하여 charset 분류
			org.jsoup.nodes.Document doc = Jsoup.parse(new File(fileList.get(cnt)),"UTF-8");
			Elements meta2 = doc.getElementsByTag("meta");
			String charset = meta2.toString();
			STRINGTEST = charset.contains("ks_c_5601-1987");
			if(STRINGTEST) {
				//ks_c_5601-1987
				file_reader = new BufferedReader(new InputStreamReader(new FileInputStream(readfile), "euc-kr"));
			}else {
				file_reader = new BufferedReader(new InputStreamReader(new FileInputStream(readfile), "UTF8"));
			}
			
			String line = "";
			while((line = file_reader.readLine()) != null){
                str += line;
            }
			
            file_reader.close();
	        }catch (FileNotFoundException e) {
	            // TODO: handle exception
	        }catch(IOException e){
	            System.out.println(e);
	        }
	
		return str;
	}
	
	//날짜 빼기
	public String fileDate(String str) {
		//String Date = new String();
		//String str = fileRead(cnt);
		
		String sent = new String();
		if(str.contains("Sent")){
			String target = "Sent:";
			int target_num = str.indexOf(target);
			
			if(str.contains("Original Message"))
				sent = "-";
			else
				//sent = str.substring(target_num+7,target_num+39);
				sent = str.substring(target_num+7,(str.substring(target_num).indexOf("To")+target_num));
			//System.out.println(target_num + ", " + str.substring(target_num).indexOf("To")+target_num);
			
			return sent;
		}
		else
			return "-";
	}
	
	@RequestMapping(value="fileopen")
	public String fileopen(HttpServletRequest request, Model model) {
		model.addAttribute("result",(Integer.parseInt(request.getParameter("cnt")))+1);
		model.addAttribute("file",fileList.get((Integer.parseInt(request.getParameter("cnt")))));
		String str = new String();
		str = fileRead(Integer.parseInt(request.getParameter("cnt")));
		model.addAttribute("show",str);
		
		/*
		try{
			
            //파일 객체 생성
			File readfile = new File(fileList.get((Integer.parseInt(request.getParameter("cnt")))));
            //입력 스트림 생성
			BufferedReader file_reader = null;
			
			//jsoup 사용하여 charset 분류
			org.jsoup.nodes.Document doc = Jsoup.parse(new File(fileList.get((Integer.parseInt(request.getParameter("cnt"))))),"UTF-8");
			Elements meta2 = doc.getElementsByTag("meta");
			String charset = meta2.toString();
			STRINGTEST = charset.contains("ks_c_5601-1987");
			if(STRINGTEST) {
				//ks_c_5601-1987
				file_reader = new BufferedReader(new InputStreamReader(new FileInputStream(readfile), "euc-kr"));
			}else {
				file_reader = new BufferedReader(new InputStreamReader(new FileInputStream(readfile), "UTF8"));
			}
			
			String line = "";
			while((line = file_reader.readLine()) != null){
                str += line;
            }
			
			
			//FW 메일 sent 혹은 날짜 빼내기
			
			String sent = new String();
			if(str.contains("Sent")){
				String target = "Sent";
				int target_num = str.indexOf(target);
				sent = str.substring(target_num+10,(str.substring(target_num).indexOf("To")+target_num)-7);
				System.out.println(sent);
			}
			else if(str.contains("보낸 날짜")) {
				String target = "Sent";
				int target_num = str.indexOf(target);
				//sent = str.substring(target_num,30+target_num);
				System.out.println(target_num);
			}
			
			
			//File file = new File(fileList.get((Integer.parseInt(request.getParameter("cnt")))));
            
            model.addAttribute("show",str);
            file_reader.close();
	        }catch (FileNotFoundException e) {
	            // TODO: handle exception
	        }catch(IOException e){
	            System.out.println(e);
	        }
	        */
	
		return "fileopen";
	}
	
	
	@RequestMapping(value="file",method=RequestMethod.POST)
	public String file(@RequestParam(defaultValue="1") int curPage, HttpServletRequest request, Model model) throws IOException, SolrServerException{
		fileList = new ArrayList<String>();
		List<String> fileDate = new ArrayList<String>();
	
		String keyword = request.getParameter("keyword");
		SolrClient client = new HttpSolrClient.Builder("http://localhost:8983/solr/gettingstarted").build();
		SolrQuery query = new SolrQuery();	
		
		
		
		//jstl
		String output = new String("검색어 '"+ keyword+"' 포함된 문서<br/>");
		keyword = keyword.replace("and","AND");
		keyword = keyword.replace("or","OR");
		System.out.println(keyword);
		if(keyword.length()>1) {
			
			
			query.setQuery(keyword);
			query.setRows(50000);
			query.addField("resourcename");
			QueryResponse response = client.query(query);
			SolrDocumentList results = response.getResults();
			
			long searchNum = response.getResults().getNumFound();
			output = output + "검색 결과 총 : " + searchNum + "개" ;
			
			//날짜받기
			SolrQuery queryDate = new SolrQuery();
			queryDate.setQuery(keyword);
			queryDate.setRows(50000);
			queryDate.addField("content");
			QueryResponse responseD = client.query(queryDate);
			SolrDocumentList resultsD = responseD.getResults();
			

			for(int i=0;i<results.size(); ++i) {
				String str = new String();
				str = str+results.get(i);
				String target = "D:";
				int target_num = str.indexOf(target);
				String result;
				result = str.substring(target_num,(str.substring(target_num).indexOf("}")+target_num));
				fileList.add(result);
				
				
				
				
				String str2  = new String();
				str2 = str2 + resultsD.get(i);
				
				String sent = new String();
				/*if(str2.contains("Sent")){
					String target2 = "Sent";
					int target_num2 = str2.indexOf(target2);
					sent = str2.substring(target_num2+10,(str2.substring(target_num2).indexOf("To")+target_num2)-7);
					//System.out.println("d");
					
					//str2=sent;
				}
				else
					sent="-";*/
				sent = fileDate(str2);
				fileDate.add(sent);
			}
			model.addAttribute("ORresult",output);
			model.addAttribute("fileList",fileList);
			model.addAttribute("list2",fileDate); 
			return "/file";}
		else
			model.addAttribute("ORresult","2글자 이상 입력하시오");
			return "/file";
		}
	
}
