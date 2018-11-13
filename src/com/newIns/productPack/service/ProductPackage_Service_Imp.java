package com.newIns.productPack.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.newIns.dao.NiAdInfoMapper;
import com.newIns.model.NiAdInfo;
import com.newIns.productPack.dao.ProductPackage_Dao;
import com.newIns.productPack.pojo.ProductPackage;
import com.newIns.tools.AjaxResult;
import com.newIns.tools.FileUtil;
import com.newIns.tools.StrUtils;

@Service
public class ProductPackage_Service_Imp implements ProductPackage_Service {
	@Autowired
	private ProductPackage_Dao productPackage_Dao;
	@Autowired
	private NiAdInfoMapper adInfoMapper;
	
	// 列表查询
	public void list_ProductPackage(HttpServletRequest request, Model model) {
		
		HashMap<String, Object> dataMap = new HashMap<>();
		
		
		List<ProductPackage> list_ProductPackage = productPackage_Dao.list_ProductPackage(dataMap);
		
		model.addAttribute("list_ProductPackage", list_ProductPackage);
		
	}

	// 保存
	public AjaxResult save_ProductPackage(HttpServletRequest request,Model model,
			MultipartFile picUrl, MultipartFile unlockPic, MultipartFile unlockedPic,MultipartFile pendingOpenPic) {
		
		String packageId_Str = request.getParameter("packageId");  // 添加和编辑一个页面， 传来一个参数用来区分
		
		String title = request.getParameter("title");
		String subtitle = request.getParameter("subtitle");
		String price_str = request.getParameter("price");
		String discount_str = request.getParameter("discount");
		String tag1 = request.getParameter("tag1");
		String tag2 = request.getParameter("tag2");
		String tag3 = request.getParameter("tag3");
		String introduce = request.getParameter("introduce");
		String adId_str = request.getParameter("adId");
		String channelId_str = request.getParameter("channelId");
		String column1Name = request.getParameter("column1Name");
		String column2Name = request.getParameter("column2Name");
		
		// 产品包id
		Integer packageId = StrUtils.changeToInt(packageId_Str);
		
		Integer adId = StrUtils.changeToInt(adId_str);
		Integer channelId = StrUtils.changeToInt(channelId_str);
		Double price = null;
		if(StrUtils.isNotEmpty(price_str)){
			price = Double.valueOf(price_str);
		}
		Double discount = null;
		if(StrUtils.isNotEmpty(discount_str)){
			discount = Double.valueOf(discount_str);
		}
		
		ProductPackage productPackage = new ProductPackage();
		// 上传图片
		String fileDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		
		try {
			Map<String, Object> uploadFile_picUrl = FileUtil.uploadFile(request, picUrl, "package/"+fileDate);
			Map<String, Object> uploadFile_unlockPic = FileUtil.uploadFile(request, unlockPic, "package/"+fileDate);
			Map<String, Object> uploadFile_unlockedPic = FileUtil.uploadFile(request, unlockedPic, "package/"+fileDate);
			Map<String, Object> uploadFile_pendingOpenPic = FileUtil.uploadFile(request, pendingOpenPic, "package/"+fileDate);
			
			String picUrl_jdbcUrl = (String) uploadFile_picUrl.get("jdbcUrl");
			String unlockPic_jdbcUrl = (String) uploadFile_unlockPic.get("jdbcUrl");
			String unlockedPic_jdbcUrl = (String) uploadFile_unlockedPic.get("jdbcUrl");
			String pendingOpenPic_jdbcUrl = (String) uploadFile_pendingOpenPic.get("jdbcUrl");
			
			productPackage.setPicUrl(picUrl_jdbcUrl);
			productPackage.setUnlockPic(unlockPic_jdbcUrl);
			productPackage.setUnlockedPic(unlockedPic_jdbcUrl);
			productPackage.setPendingOpenPic(pendingOpenPic_jdbcUrl);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		productPackage.setTitle(title);
		productPackage.setSubtitle(subtitle);
		productPackage.setPrice(price);
		productPackage.setDiscount(discount);
		productPackage.setTag1(tag1);
		productPackage.setTag2(tag2);
		productPackage.setTag3(tag3);
		productPackage.setIntroduce(introduce);
		productPackage.setChannelId(1);
		productPackage.setStatus(1); // 1.已上架  , 2.待上架 
		productPackage.setAdId(adId);
		productPackage.setChannelId(channelId);
		productPackage.setColumn1Name(column1Name);
		productPackage.setColumn2Name(column2Name);
		
		if(packageId != null){
			productPackage.setId(packageId);
			// 编辑
			try {
				productPackage_Dao.update_key(productPackage);
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult();
			}
			
		}else{
			// 添加
			try {
				productPackage_Dao.insert_ProductPackage(productPackage);
			} catch (Exception e) {
				e.printStackTrace();
				return AjaxResult.errorResult();
			}
			
		}
		
		return AjaxResult.successResult();
	}

	// 添加或编辑 前查询数据库进行展示
	public void add_ProductPackage(HttpServletRequest request, Model model) {
		
		String productPackage_Id_str = request.getParameter("productPackage_Id");
		// 如果前端传来 id , 则为编辑, 根据id查询产品包信息
		if(StrUtils.isNotEmpty(productPackage_Id_str)){
			Integer productPackage_Id = Integer.valueOf(productPackage_Id_str);
			
			ProductPackage productPackage = productPackage_Dao.select_key(productPackage_Id);
			
			model.addAttribute("productPackage", productPackage);
		}
		
		// 查询所有广告信息
		List<NiAdInfo> selectList_NiAdInfo = adInfoMapper.selectList();
		
		model.addAttribute("List_NiAdInfo", selectList_NiAdInfo);
	}

	//上下架
	@Override
	public AjaxResult update_ProductPackage_status(HttpServletRequest request,
			Model model) {
		
		String productPackageId_list_str = request.getParameter("productPackageId_list");
		
		if(StrUtils.isEmpty(productPackageId_list_str)){
			return AjaxResult.errorResult("未选中");
		}
		
		String[] productPackageId_list_arr = productPackageId_list_str.split("!");
		
		ArrayList<Integer> arrayList = new ArrayList<>();
		
		for(int i=0;i <productPackageId_list_arr.length ; i++){
			
			String productPackageId_str = productPackageId_list_arr[i];
			
			Integer productPackageId = StrUtils.changeToInt(productPackageId_str);
			
			// 修改状态
			if(productPackageId!=null){
				
				arrayList.add(productPackageId);
			}
			
		}
		
		try {
			productPackage_Dao.update_up_down(arrayList);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.errorResult();
		}
		
		return AjaxResult.successResult();
	}
	
	
	
	
}
