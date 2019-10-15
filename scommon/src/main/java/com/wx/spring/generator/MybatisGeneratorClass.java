package com.wx.spring.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * mybatisplus  自动生成代码类
 * @Description:
 * @Title: MybatisGeneratorClass
 * @author wangxin
 * @date 2019年10月12日
 */
public class MybatisGeneratorClass {
	private  static final String URL = "jdbc:mysql://rm-bp1lt3gi5m0570f24o.mysql.rds.aliyuncs.com:3306/eval_back?useSSL=false&serverTimezone=Asia/Hong_Kong&allowMultiQueries=true";
	private  static final String USERNAME = "evalowner";
	private  static final String PWD = "c3U4xRaMM10rSQMmk";
	
	/**
	 * 生成位置
	 */
	private  static final String ADDR = "F://GeneratorCode";
	
	
	public static void main(String[] args) throws InterruptedException {
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(ADDR);
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setAuthor("author");

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
         gc.setMapperName("%sMapper");
         gc.setXmlName("%sMapper");
         gc.setServiceName("%sService");
         gc.setServiceImplName("%sServiceImpl");
         gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        /*dsc.setTypeConvert(new MySqlTypeConvert(){
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                System.out.println("转换类型：" + fieldType);
                return super.processTypeConvert(fieldType);
            }
        });*/

        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUrl(URL);
        dsc.setUsername(USERNAME);
        dsc.setPassword(PWD);
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        // strategy.setTablePrefix(new String[] { "tlog_", "tsys_" });// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(new String[] { "user" }); // 需要生成的表
//        strategy.setFieldNaming(NamingStrategy.underline_to_camel);//字段生成策略
//         strategy.setExclude(new String[]{"test"}); // 排除生成的表
       
         
         
        mpg.setStrategy(strategy);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.wx.spring");
        pc.setModuleName("report");
        mpg.setPackageInfo(pc);

        // 执行生成
        mpg.execute();
    }
}
