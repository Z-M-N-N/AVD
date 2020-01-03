import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Stack;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PostMethodTest {
	static String nexturl="https://www.867uy.com/shipin/list-%E7%9F%AD%E8%A7%86%E9%A2%91.html";
	static String downurl="https://www.867uy.com";

	static Stack<String> stack=new Stack<>();
	static HttpURLConnection conn = null;
	
	public static void main(String[] args)  {
		int pagenum=3;
//		FileReader fr;
//		int i=0;
//		URL ss=new geturl().geturl();
//	File child=new File(ss.getPath());
//	System.out.println(child.getParent().toString());
//	try {
//		fr = new FileReader(new File(child.getParentFile().getParent()+"/info.txt"));
//			BufferedReader br=new BufferedReader(fr);
//			String url=br.readLine();
//			System.out.println(url);
//			nexturl=url;
//			url=br.readLine();
//		System.out.println(url);
//			downurl=url;
//			url=br.readLine();
//		System.out.println(url);
//		pagenum=Integer.parseInt(url);
//		
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("没配置文件");		
//			}	
//		
         
		
		PostMethodTest down=new PostMethodTest();
		down.downLoad(nexturl,conn,pagenum);
		
	}
	 public void downLoad(String url,HttpURLConnection conn,int pagenum) {
		 int markall=0;	
		 
		 do{
			 try{
         	System.out.println("++++++++++");
             URL realUrl = new URL(url);
             conn = (HttpURLConnection) realUrl.openConnection();
             conn.setRequestMethod("GET");
             conn.setUseCaches(false);
             conn.setReadTimeout(8000);
             conn.setConnectTimeout(8000);
             conn.setInstanceFollowRedirects(false);
             conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
             int code = conn.getResponseCode();
             if (code == 200) {
                 InputStream is = conn.getInputStream();
                 BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                 StringBuffer buffer = new StringBuffer();
                 String line = "";
                 while ((line = in.readLine()) != null){
                     buffer.append(line);
                 }
                 String result = buffer.toString();
                 //subscriber是观察者，在本代码中可以理解成发送数据给activity
                 //System.out.println(result+123);
                 Document dc=Jsoup.parseBodyFragment(result);
                 Elements el=dc.getElementsByClass("shown");
                // System.out.println(el.size());
                 System.out.println("_____________");
                 int i=1;
                 for(Element it: el){
                	 Elements ela=it.getElementsByTag("a");
                	 download.downLoad(downurl+ela.get(0).attr("href"));
                	 System.out.println(i);
                	 i++;
                	 //System.out.println(downurl+ela.get(0).attr("href"));
                 }
                 
                 int mark=1;
                 Elements nextel=dc.getElementsByClass("pagination");
                 Elements nextela=nextel.get(0).getElementsByTag("a");
                 Element nexthref=nextela.get(nextela.size()-2);
                // System.out.println(nexthref.attr("href"));
                 if(nextela.get(nextela.size()-2).attr("href").equals(nextela.get(nextela.size()-1).attr("href"))){
                	 mark=0;
            
                 }
                 
                 if(mark!=0&&pagenum>0){
                	 nexturl=downurl+nexthref.attr("href");
                	// markall=1;
                	 System.out.println(nexthref.attr("href"));
                	 downLoad(downurl+nexthref.attr("href"),conn,pagenum--);
                 }
             }
             markall=1;
			 }catch (Exception e) {
				// TODO: handle exception
			}
		 	}while(markall==0);
        
	}
}

