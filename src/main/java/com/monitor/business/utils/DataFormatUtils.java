package com.monitor.business.utils;

import org.springframework.stereotype.Component;

@Component
public class DataFormatUtils {

    public static byte hexToByte(String inHex){  
         return (byte)Integer.parseInt(inHex,16);  
    } 
	
	public static byte[] hexStr2Bytes(String src)    
	{    
	    int m=0,n=0;    
	    int l=src.length()/2;    
	    byte[] ret = new byte[l];    
	    for (int i = 0; i < l; i++)    
	    {    
	    	char ch1= src.charAt(2*i);
	    	switch(ch1){
	    	  case('a'): m=10; break;
	    	  case('b'): m=11; break;
	    	  case('c'): m=12; break;
	    	  case('d'): m=13; break;
	    	  case('e'): m=14; break;
	    	  case('f'): m=15; break;
	    	  default: m=Integer.parseInt(""+ch1);break;
	    	}
	    	char ch2= src.charAt(2*i+1);
	    	switch(ch2){
	    	  case('a'): n=10; break;
	    	  case('b'): n=11; break;
	    	  case('c'): n=12; break;
	    	  case('d'): n=13; break;
	    	  case('e'): n=14; break;
	    	  case('f'): n=15; break;
	    	  default: n=Integer.parseInt(""+ch2);break;
	    	}
	    	 int value=m*16+n;
	         if(value>127){
	        	 value-=256;
	        	 byte[] intToBytes = DataFormatUtils.intToBytes(value);
	        	// String intToHexString = DataFormatUtils.intToHexString(value);
	        	 ret[i] =intToBytes[0] ; 
	         }  else{
	        	 byte[] intToBytes = DataFormatUtils.intToBytes(value);
		         ret[i] =intToBytes[0] ; 
	         }
	    }    
	    return ret;    
	}    
	public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
	/*整数转换成单个字节数组*/
	/**  
	   * 将int数值转换为占两个字节的byte数组，本方法适用于(高位在后，低位在前)的顺序。  和bytesToInt2（）配套使用 
	   */  
	public static byte[] intToBytes( int value )   
	{   
	    byte[] src = new byte[2];  
	    src[1] =  (byte) ((value>>8) & 0xFF);    
	    src[0] =  (byte) (value & 0xFF);                  
	    return src;   
	}  
	/**  
	   * 将int数值转换为占两个字节的byte数组，本方法适用于(高位在后，低位在前)的顺序。  和bytesToInt2（）配套使用 
	   */    
	public static byte[] intToBytes2(int value)   
	{   
	    byte[] src = new byte[2];  
	    src[0] = (byte) ((value>>8)&0xFF);    
	    src[1] = (byte) (value & 0xFF);       
	    return src;  
	}  
	/**  
	    * byte数组中取int数值，本方法适用于(低位在前，高位在后)的顺序，和和intToBytes（）配套使用 
	    *   
	    * @param src  
	    *            byte数组  
	    * @param offset  
	    *            从数组的第offset位开始  
	    * @return int数值  
	    */    
	public static int bytesToInt(byte[] src, int offset) {  
	    int value;    
	    value = (int) ((src[offset] & 0xFF)   
	            | ((src[offset+1] & 0xFF)<<8));  
	    return value;  
	}  
	/**  
	    * byte数组中取int数值，本方法适用于(低位在后，高位在前)的顺序。和intToBytes2（）配套使用 
	    */  
	public static int bytesToInt2(byte[] src, int offset) {  
	    int value;    
	    value = (int) ( ((src[offset] & 0xFF)<<8) 
	            |(src[offset+1] & 0xFF));  
	    return value;  
    } 
	
	public static String intToHexString(int i){
		  String str = Integer.toHexString(i);
		 /* System.out.println(str);*/
		  int n = str.length();
		  String [] ss = {"0000","000","00","0",""};
		  str = ss[n] + str;
		  return str;
	  }
	/*@Test 
	public void myTest(){
		System.out.println(intToHexString(1));
		System.out.println(intToHexString(201));
		System.out.println(intToHexString(3333));
		System.out.println(intToHexString(10000));
	}*/
	public static String getCrc16(byte[] arr_buff) {  
        int len = arr_buff.length;  
        //预置 1 个 16 位的寄存器为十六进制FFFF, 称此寄存器为 CRC寄存器。  
        int crc = 0xFFFF;  
        int i, j;  
        for (i = 0; i < len; i++) {  
            //把第一个 8 位二进制数据 与 16 位的 CRC寄存器的低 8 位相异或, 把结果放于 CRC寄存器  
            crc = ((crc & 0xFF00) | (crc & 0x00FF) ^ (arr_buff[i] & 0xFF));  
            for (j = 0; j < 8; j++) {  
                //把 CRC 寄存器的内容右移一位( 朝低位)用 0 填补最高位, 并检查右移后的移出位  
                if ((crc & 0x0001) > 0) {  
                    //如果移出位为 1, CRC寄存器与多项式A001进行异或  
                    crc = crc >> 1;  
                    crc = crc ^ 0xA001;  
                } else  
                    //如果移出位为 0,再次右移一位  
                    crc = crc >> 1;  
            }  
        }
        String str = Integer.toHexString(crc);
        if(str.length()==3){
        	str="0"+str;
        }
    /*    System.out.println(str);*/
        String str1=str.substring(0,2);
        String str2=str.substring(2);
        return str2+str1;  
    } 
	/*Modbus读取寄存器*/
  public  static  String  modbusReadRegister(int slaveId,int start,int readLenth){
	  String id=Integer.toHexString(slaveId);
	  if(id.length()==1){
		  id="0"+id;
	  }
	  String code=id+"03"+intToHexString(start)+intToHexString(readLenth);
	  byte[] hexStr2Bytes = hexStr2Bytes(code);
	  String crc16 = getCrc16(hexStr2Bytes);
	  return code+crc16;
  }
    /*modbus写单个寄存器*/
  public static String modbusWriteSingleRegister(int slaveId,int reg,int value){
	  String id=Integer.toHexString(slaveId);
	  if(id.length()==1){
		  id="0"+id;
	  }
	  String code=id+"06"+intToHexString(reg)+intToHexString(value);
	  byte[] hexStr2Bytes = hexStr2Bytes(code);
	  String crc16 = getCrc16(hexStr2Bytes);
	  return code+crc16;
  }
}
