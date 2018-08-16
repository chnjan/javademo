/**
 * 
 */
package com.chnjan.other;

/**
 * @author chenjian
 * @date 2018年3月10日
 */
public class AnInterfaceImpl implements AnInterface {

	/* (non-Javadoc)
	 * @see com.chnjan.other.AnInterface#dosome(java.lang.String, java.lang.String)
	 */
	@Override
	public String dosome(String a, String b) {
		System.out.println("doing something a:"+a+"   b:"+b);
		return "success";
	}

}
