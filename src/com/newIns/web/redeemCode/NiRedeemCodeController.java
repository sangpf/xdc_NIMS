package com.newIns.web.redeemCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newIns.service.redeemCode.RedeemCodeService;

/**
 * 静态优惠码
 * @author sangpf
 */
@Controller
@RequestMapping("/platform")
public class NiRedeemCodeController {
	
	@Autowired
	private RedeemCodeService redeemCodeService;
	
	/**
	 * 初始化静态优惠码
	 */
	@RequestMapping("/InitializationRedeemCode.do")
	public void InitializationRedeemCode(){
		
		redeemCodeService.InitializationRedeemCode();
		
		System.out.println("初始化完成");
		
	}
	
}
