# ViewPagerDesignAnim
## 自定义View动画效果，ViewPager手势操作以及动画效果展示
# 作者 #
---

WYH_Healer 

>Csdn:[WYH_Healer的博客](http://blog.csdn.net/wyh_healer)

---
在本篇代码中详细的解读了每一行代码的作用性，以及参考已有的文章代码进行知识梳理，有兴趣的同学可以学习一下，互帮互助，不做重复的轮胎~
 
#### 首页的轮播图片控制动画是通过ViewPager.PageTransformer里实现transformPage方法
## ![image](https://github.com/wyhnihaook/ViewPagerDesignAnim/raw/master/img-holder/1.png)
#### 友好的欢迎页面体验
## ![image](https://github.com/wyhnihaook/ViewPagerDesignAnim/raw/master/img-holder/2.png)
#### 自定义View进度条更改实现动画效果（类似加速仪）
## ![image](https://github.com/wyhnihaook/ViewPagerDesignAnim/raw/master/img-holder/3.png)
#### 通过实现ViewPager.OnPageChangeListener, ViewPager.PageTransformer，具体逻辑代码在onPageScrolled方法中实现，并且设置ViewPager的属性“clipToPadding”和各个Padding实现
## ![image](https://github.com/wyhnihaook/ViewPagerDesignAnim/raw/master/img-holder/4.png)

>下载的童鞋希望多多star and fork；
