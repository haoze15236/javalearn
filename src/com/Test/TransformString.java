package com.Test;

import java.io.UnsupportedEncodingException;

public class TransformString {
	public static void main(String[] args) {
		String str = "é©³åèç¹ãé©³ååå ï¼å®¡æ¹é©³åå¿å¡«ä¸è½ä¸ºç©º";
		String str2 = "集商市民中心";
		String str4 = "集;商;市;民中心";
		String str3 = "_request_data={{\"parameter\":{\"url\":\"http://cms.vinda.com:8912/api/oaLog/receiveOaResult\",\"list_id\":\"1929543\",\"params\":\"interfaceType:OAF1020632_2\nreportId:9B333ADE540D5026E0532D6D0A0AE14E\noaNumber:OA11220010277\napprovalStatus:COMPLETELY_APPROVED\nbudgetNumber:集商市民中心201061\napprover:胡宝燕\nmessage:\nnode:商用市场部推广主管及经理\nhistory:[{\"approvalStatus\":\"同意\",\"approver\":\"文沃良\",\"message\":\"本项目是机构类物业的地标，望支持！\",\"node\":\"市办经理\"},{\"approvalStatus\":\"同意\",\"approver\":\"黄婵\",\"message\":\"\",\"node\":\"省办经理\"},{\"approvalStatus\":\"同意\",\"approver\":\"谭社壮\",\"message\":\"\",\"node\":\"区域商用管理部经理\"},{\"approvalStatus\":\"同意\",\"approver\":\"温启才\",\"message\":\"\",\"node\":\"区域总监\"},{\"approvalStatus\":\"同意\",\"approver\":\"杨绮媚\",\"message\":\"首次申请，申请单品型号和单价正确。\",\"node\":\"商用事业部客户维护及数据分析组\"},{\"approvalStatus\":\"同意\",\"approver\":\"林敏瑜\",\"message\":\"关于没推广封闭系统的说明：a.项目原来是采购金红叶的通用款产品，同时涉及政府财政资金的采购，封闭性系统无法多家竞价，导致封闭系统难以推广；b、为了抢下标杆项目，更好的推广多康产品，增加多康产品露出，因此选择半封闭的系统产品T2和H2；c、本项目是覆盖的是深圳市政府，对深圳区域各级的行政机关类的物业项目推广有重大示范性意义。经过与经销商沟通，经销商同意承担30%的大卷纸架费用和全部擦手纸架费用（13000元），故申请130个555000 T2迷你大卷纸架\",\"node\":\"商用事业部客户维护及数据分析组\"},{\"approvalStatus\":\"同意\",\"approver\":\"林健新\",\"message\":\"\",\"node\":\"商用管理部经理\"},{\"approvalStatus\":\"同意\",\"approver\":\"胡宝燕\",\"message\":\"[无需重复审批/No need to repeat the examination and approval/Kelulusan tidak perlu diulangi]\",\"node\":\"商用事业部总监\"},{\"approvalStatus\":\"同意\",\"approver\":\"施雯静\",\"message\":\"\",\"node\":\"财务管理中心\"},{\"approvalStatus\":\"同意\",\"approver\":\"胡宝燕\",\"message\":\"\",\"node\":\"商用市场部推广主管及经理\"}]\",\"method\":\"POST\"}}}";
		System.out.println(str4.replaceAll(";", ","));
//		System.out.println(str3.length());
//		try {
//			str = new String(str.getBytes("iso8859-1"),"utf-8");
//			System.out.println(str);
//			str2 = new String(str2.getBytes("utf-8"),"iso8859-1");
//			System.out.println(str2);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
	}
}
