# DouYin
自己开发的抖音App
## 欢迎页
<img src="https://github.com/BruceAnda/DouYin/blob/master/screenshot/splash.png" width="480" height="800" alt="抖音闪屏页"/><br/>
## 目前正在开发主页
cameralibrary: 相机库，包括渲染渲染线程、渲染引擎等流程

facedetectlibrary: Face++人脸关键点SDK库。结合landmarklibrary库做人脸关键点处理。

filterlibrary：滤镜库。该库存放各个滤镜以及资源处理等工具。

imagelibrary: 图片编辑库。暂时该库仅有的滤镜处理和保存功能，目前由于正在编写短视频编辑功能的，该库目前暂时没完善。

landmarklibrary: 关键点处理库。该库用于归一化的关键点处理，用在filterlibrary中处理滤镜、贴纸等处理。

medialibrary: 短视频编辑库。用于短视频编辑实时预览的播放器、音频裁剪器、视频合成器等全套C++代码。 音视频裁剪器、视频合成器目前仍在开发阶段，敬请期待。

pickerlibrary: 媒体选择库。用于选择媒体库中的图像、视频。

utilslibrary: 共用工具库。bitmap处理、文件处理、字符串处理的封装工具。

videolibrary: 视频编辑库。目前该库处于计划实现状态，由于短视频播放器、短视频合成器等工具还没实现，目前该库暂时还没实现，敬请期待。

shortvideolibrary 短视频库。视频录制功能在这里完成

douyinmain 主项目，实现抖音功能