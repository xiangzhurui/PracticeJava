package personal.xzr.practice.util.excel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 错误信息实体
 *
 * @author XiangZhuRui
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelErrorBean {
    // 入库批次号
    private String guideNum;
    // 数据有误的行号
    private Integer rowNumB;
    // 导入次数,默认值[1]
    private Integer guideTimes;
    // 错误原因
    private String errorreason;
    // 错误行，默认值[1]
    private Integer errorrow;
    // 创建日期
    private Date makedate;
    // 创建时间
    private String maketime;
    // 修改日期
    private Date modifydate;
    // 修改时间
    private String modifytime;
    // 操作人
    private String operator;

}