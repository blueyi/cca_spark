/*
 * csayhello.cpp
 * Copyright (C) 2017 dutoeserver <dutoeserver@A0835-Server-2>
 *
 * Distributed under terms of the MIT license.
 */

#include "include/myjni_hello.h"
#include <iostream>

JNIEXPORT void JNICALL Java_myjni_hello_helloWorld(JNIEnv *env, jobject obj)
{
    std::cout << "Hello world!" << std::endl;
}
