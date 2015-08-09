package com.example.classifymenu;

/**
 * created anumbrella
 * 
 * 2015 7 27 数据常量类
 * */
public class DataModel {

	// 第一个listView的图片资源数组(12张图片)

	public static int[] LISTVIEWIMG = new int[] { R.drawable.ic_category_10,
			R.drawable.ic_category_20, R.drawable.ic_category_30,
			R.drawable.ic_category_40, R.drawable.ic_category_45,
			R.drawable.ic_category_50, R.drawable.ic_category_55,
			R.drawable.ic_category_60, R.drawable.ic_category_65,
			R.drawable.ic_category_70, R.drawable.ic_category_80,
			R.drawable.ic_category_85 };
	// 第一个listView的文本数据数组(12个数据文本)
	public static String[] LISTVIEWTXT = new String[] { "热门分类", "美食", "购物",
			"休闲娱乐", "运动健身", "丽人", "结婚", "酒店", "爱车", "亲子", "生活服务", "家装" };

	// 第二个listView文本数据数组(12,..)
	public static String[][] MORELISTVIEWTXT = new String[][] {
			{ "全部分类", "小吃快餐", "咖啡厅", "电影院", "KTV", "茶馆", "足疗按摩", "超市/便利店",
					"银行", "经济型酒店", "景点/郊游", "公园", "美发" },
			{ "全部美食", "小吃快餐", "西餐", "火锅", "北京菜", "川菜", "日本", "面包甜点", "粤菜",
					"韩国料理", "自助餐", "浙江菜", "云南菜", "湘菜", "东南亚菜", "西北菜", "鲁菜",
					"东北菜", "素菜", "新疆菜", "海鲜", "清真菜", "贵州菜", "湖北菜", "其他" },
			{ "全部购物", "综合商场", "服饰鞋包", "超市/便利店", "特色集市", "品牌折扣店", "眼镜店", "珠宝饰品",
					"化妆品", "运动户外", "食品茶酒", "书店", "数码产品", "药店", "京味儿购物", "亲子购物",
					"花店", "家具建材", "更多购物场所" },
			{ "全部休闲娱乐", "咖啡厅", "KTV", "景点/郊游", "电影院", "酒吧", "公园", "温泉", "文化艺术",
					"足疗按摩", "洗浴", "茶馆", "游乐游艺", "密室", "采摘/农家乐", "桌面游戏", "台球馆",
					"DIY手工坊", "休闲网吧", "真人CS", "棋牌室", "轰趴馆", "私人影院", "更多休闲娱乐" },
			{ "全部运动健身", "健身中心", "游泳馆", "瑜伽", "羽毛球馆", "台球馆", "舞蹈", "体育场馆",
					"高尔夫场", "网球场", "武术场馆", "篮球场", "保龄球馆", "足球场", "乒乓球馆",
					"更多体育运动" },
			{ "全部丽人", "美发", "美容/SPA", "齿科", "美甲", "化妆品", "瑜伽", "瘦身纤体", "舞蹈",
					"个性写真", "整形" },
			{ "全部结婚", "婚纱摄影", "婚宴酒店", "婚纱礼服", "婚庆公司", "婚戒首饰", "个性写真", "彩妆造型",
					"婚礼小礼品", "婚礼跟拍", "婚车租赁", "司仪主持", "婚房装修", "更多婚礼服务" },
			{ "全部酒店", "经济型酒店", "五星级酒店", "度假村", "四星级酒店", "三星级酒店", "农家院",
					"公寓式酒店", "青年旅社", "精品酒店", "更多酒店住宿" },
			{ "全部爱车", "维修保养", "驾校", "停车场", "4S店/汽车销售", "加油站", "配件/车饰", "汽车租赁",
					"汽车保险" },
			{ "全部亲子", "亲子摄影", "幼儿教育", "亲子游乐", "孕产护理", "亲子购物", "更多亲子服务" },
			{ "全部生活服务", "医院", "银行", "齿科", "宠物", "培训", "快照/冲印", "学校", "旅行社",
					"购物网站", "干洗店", "家政", "奢侈品护理", "商务楼", "小区", "更多生活服务" },
			{ "全部家装", "家具家装", "家用电器", "建材", "家装卖场", "装修设计" } };

	// 数组的大小为(28)
	public static String[] toolist = new String[] { "特价", "潮流女装", "品牌男装",
			"内衣配饰", "精品童装", "家用电器", "手机数码", "电脑办公", "个护化妆", "母婴频道", "美食", "海鲜",
			"家居家纺", "整车车品", "鞋靴箱包", "运动户外", "图书", "玩具乐器", "钟表", "居家生活", "珠宝饰品",
			"音像制品", "家具建材", "计生情趣", "营养保健", "奢侈礼品", "生活服务", "旅游出行" };

	// 数组的大小为(28)
	public static int[] iconList = new int[] { R.drawable.tejia,
			R.drawable.nvzhuang, R.drawable.nanzhuang, R.drawable.neiyi,
			R.drawable.tongzhuang, R.drawable.dianqi, R.drawable.shouji,
			R.drawable.diannao, R.drawable.huazhuang, R.drawable.muying,
			R.drawable.meishi, R.drawable.haixian, R.drawable.jiaju,
			R.drawable.jiaju, R.drawable.nvxie, R.drawable.nanzhuang,
			R.drawable.shuji, R.drawable.wanju, R.drawable.zhongbiao,
			R.drawable.shouji, R.drawable.shipin, R.drawable.yingshi,
			R.drawable.muying, R.drawable.qingqu, R.drawable.haixian,
			R.drawable.shechipin, R.drawable.jiaju, R.drawable.lvxing };
	// 父目录ExpandableListView的图片资源数组(4)
	public static int[] EXPANDABLE_LISTVIEW_IMG = new int[] {
			R.drawable.ic_category_40, R.drawable.ic_category_10,
			R.drawable.ic_category_20, R.drawable.ic_category_85 };

	// 父目录ExpandleableListView的文本数据数组(4)
	public static String[] EXPANDABLE_LISTVIEW_TXT = new String[] { "热门分类",
			"美食", "购物", "家装" };

	// 子目录ExpandleableListView的文本数据数组(4,4)
	public static String[][] EXPANDABLE_MORELISTVIEW_TXT = new String[][] {
			{ "全部分类", "小吃快餐", "咖啡厅", "电影院", "KTV", "茶馆", "足疗按摩", "超市/便利店",
					"银行", "经济型酒店", "景点/郊游", "公园", "美发" },
			{ "全部美食", "小吃快餐", "西餐", "火锅", "北京菜", "川菜", "日本", "面包甜点", "粤菜",
					"韩国料理", "自助餐", "浙江菜", "云南菜", "湘菜", "东南亚菜", "西北菜", "鲁菜",
					"东北菜", "素菜", "新疆菜", "海鲜", "清真菜", "贵州菜", "湖北菜", "其他" },
			{ "全部购物", "综合商场", "服饰鞋包", "超市/便利店", "特色集市", "品牌折扣店", "眼镜店", "珠宝饰品",
					"化妆品", "运动户外", "食品茶酒", "书店", "数码产品", "药店", "京味儿购物", "亲子购物",
					"花店", "家具建材", "更多购物场所" },
			{ "全部家装", "家具家装", "家用电器", "建材", "家装卖场", "装修设计" } };

	// 父目录ExpandableGridView的文本数据数组(14)
	public static String[] EXPANDABLE_GRIDVIEW_TXT = new String[] { "新闻", "军事",
			"微博", "体育", "娱乐", "财经", "视频", "科技", "图片", "房产", "汽车", "教育", "历史",
			"女性" };
	// 子目录ExpandableGridView的文本数据(14,..)
	public static String[][] EXPANDABLE_MOREGRIDVIEW_TXT = {
			{ "国内", "社会", "国际", "评论", "传媒", "排行", "视频", "滚动", "调查", "搜索", "航空",
					"直播" },
			{ "新闻", "图片", "中国军情", "专栏", "视频" },
			{ "注册", "名人堂", "人气热榜", "客户端", "热门微博", "随便看看" },
			{ "NBA", "中超", "欧冠", "英超", "意甲", "西甲", "德甲", "CBA", "彩票", "网球",
					"高尔夫", "综合", "图片", "赛车", "国足", "中甲", "田径" },
			{ "明星", "电影", "电视", "音乐", "韩娱", "毒蛇女", "八卦", "水煮娱", "博客", "影视打分" },
			{ "产经", "消费", "理财", "外汇", "股票", "行情", "基金", "美股", "港股", "期货", "黄金",
					"投资助手", "银行", "保险", "专栏", "博客", "股吧", "图集", "自选股" },
			{ "新闻", "娱乐", "综艺", "体育", "搞笑" },
			{ "互联网", "电信", "软件", "硬件", "创事记", "探索", "苹果汇", "数码", "创业", "手机",
					"相机", "趣图", "笔记本" },
			{ "看见", "天下", "奇趣", "历史", "摄影师" },
			{ "新房", "二手房", "租房", "家居", "最新开盘", "打折优惠", "看房图", "新闻", "资讯", "装修" },
			{ "车型", "报价", "图赏", "保养", "二手车", "新车", "降价", "导购", "试车", "答题" },
			{ "高考", "考研", "留学", "外语", "图片", "国际校", "中小学", "公务员", "博客", "专栏",
					"滚动", "高校库" },
			{ "解密", "党史", "战争", "野史", "资讯", "图片", "专题" },
			{ "情感", "美容", "八卦", "美图", "试用", "口述", "直播" } };

}
