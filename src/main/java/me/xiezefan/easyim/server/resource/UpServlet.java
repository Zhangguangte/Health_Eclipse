package me.xiezefan.easyim.server.resource;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class UpServlet
 */
@WebServlet("/UpServlet")
public class UpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("保存失败！");
		
		//		request.setCharacterEncoding("utf-8");
//        response.setCharacterEncoding("utf-8");
//        response.setHeader("content-type","text/html;charset=UTF-8");
//        try {
//            //初始化
//            SmartUpload smartUpload = new SmartUpload();
//            smartUpload.initialize(this.getServletConfig(), request, response);
////            smartUpload.upload("utf-8");
//            //获取writeOff数据的json字符串
//            String json = smartUpload.getRequest().getParameter("writeOff");
//            System.out.println("json:" + json);
//            //将json转成对象
//            //获取文件信息并保存
//            Files files = smartUpload.getFiles();
//            for (int i = 0; i < files.getCount(); i++) {
//                File file = files.getFile(i);
//                System.out.println("fileName:" + file.getFileName());
//                System.out.println("fieldName:" + file.getFieldName());
//                //获取随机字符串
//                String name = UUID.randomUUID().toString();
//                //保存路径
//                String savePath = "H:\\" + name + ".jpg";
//                //保存
//                file.saveAs(savePath);
//                System.out.println("保存成功：" + savePath);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("保存失败！");
//        };
	}

}
