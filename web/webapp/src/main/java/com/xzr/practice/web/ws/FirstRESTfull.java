package com.xzr.practice.web.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author XiangZhuRui
 *
 */
@Path("/first")
public class FirstRESTfull {
	private static final Logger log = LoggerFactory.getLogger(FirstRESTfull.class);

	@GET
	@Path("/{userId}")
	public String getParam(@PathParam(value = "userId") String userId) {
		log.info("收到的userId是：{}", userId);
		return userId;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultBean getVO(ParamsBean params) {
		log.info("ssssssss,,,,{}");
		
		ResultBean rb = new ResultBean();
		rb.setGender(params.getGender());
		rb.setName(params.getName());
		rb.setComment(params.getName() + ":::" + params.getGender());
		return rb;

	}
}
