//
// Created by jiahui.chen on 2016/1/4.
//

#include "NDK.h"

#include "com_smartman_myroadrecord_NDKTest.h"
//#include <android/log.h>
//#define  LOG_TAG  "System.out"
//#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG,  __VA_ARGS__)
//#define LOGINFO(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG,  __VA_ARGS__)





JNIEXPORT jstring JNICALL Java_com_smartman_myroadrecord_NDKTest_getStringFromNative
        (JNIEnv * env, jobject obj){
    return (*env)->NewStringUTF(env,"NDK 测试成功");
}


