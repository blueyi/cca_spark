/*
 * csayhello.cpp
 * Copyright (C) 2017 dutoeserver <dutoeserver@A0835-Server-2>
 *
 * Distributed under terms of the MIT license.
 */

#include "win_dutoe_IceAnalysis.h"
#include <iostream>

JNIEXPORT jstring JNICALL Java_win_dutoe_IceAnalysis_libIceAnalysis(JNIEnv *env, jobject obj, jstring jStr)
{
   //将Java的String转成C语言的char *
    const char *cStr = env->GetStringUTFChars(jStr, NULL); 
    if (NULL == cStr) return NULL; //如果转换失败，则返回空
    std::string tstr = cStr;  //拷贝一份字符串
    //释放cStr，如果不释放，该数组会一直存在，并且不会被自动回收
    env->ReleaseStringUTFChars(jStr, cStr);  
    std::cout << tstr << std::endl;
    //将c风格字符串转换成jstring并返回
    return env->NewStringUTF(tstr.c_str());
}
