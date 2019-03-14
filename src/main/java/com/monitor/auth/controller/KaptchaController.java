package com.monitor.auth.controller;


import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.monitor.auth.dao.KaptchaDao;
import com.monitor.auth.entity.Kaptcha;
import com.monitor.auth.util.ShiroUtils;
import com.monitor.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

@Api(description = "验证码", value = "KaptchaController")
@RestController("kaptchaController")
@RequestMapping("/kaptcha")
@EnableSwagger2
public class KaptchaController {

    @Autowired
    private KaptchaDao kaptchaDao;
    @Autowired
    private Producer producer;

    /**
     * 1、验证码工具
     */
    /*@Autowired
    DefaultKaptcha defaultKaptcha;*/

    /**
     * 生成验证码SSSS
     * @param response
     * @throws Exception
     */
    @ApiOperation(value = "生成验证码", notes = "生成验证码")
    @GetMapping("/generateKaptcha")
    //@RequiresAuthentication
    public void  generateKaptcha(HttpServletResponse response)
            throws Exception {

       /* byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();

        String rightCode="";
        try {
            // 生产验证码字符串并保存到session中
            rightCode = (String)defaultKaptcha.createText();
//          httpServletRequest.getSession().setAttribute("rightCode", rightCode);
            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(rightCode);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return ;
        }

        // 将正确验证码值+IP,保存到数据库中
        String remoteAddr = httpServletRequest.getRemoteAddr();
        Kaptcha code = new Kaptcha();
        code.setCreateTime(LocalDateTime.now());
        code.setRightCode(rightCode);
        code.setAddressIp(remoteAddr);
        kaptchaDao.insert(code);
        System.out.println(code);
        System.out.println(rightCode);
        System.out.println(remoteAddr);



        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();*/

        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    /**
     * 3、校对验证码
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @ApiOperation(value = "校对验证码")
    @GetMapping("/verifyKaptcha")
    @RequiresAuthentication
    public Result<Kaptcha> imgvrifyControllerDefaultKaptcha(String tryCode, HttpServletRequest httpServletRequest,
                                                            HttpServletResponse httpServletResponse) {
        Result<Kaptcha> result = new Result<>(null);
        String remoteAddr = httpServletRequest.getRemoteAddr();
        Kaptcha testCode = kaptchaDao.getByIp(remoteAddr);
        if(testCode.getRightCode().equals(tryCode)) {
            return result.setData(testCode);
        }else {
            return result.setData(testCode).setMessage("验证码验证失败！");
        }
    }
}

