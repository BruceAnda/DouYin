package com.bw.douyin.utils;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 *  @author 赵亮
 *  @date 2020/11/23 18:29
 *  @desc RxBus管理类
 *  @version 1.0
 */
public class RxBus {

    private static volatile  RxBus instance;
    private Subject<Object, Object> bus;

    private RxBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }

    public static RxBus getDefault() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    /**
     * 发送时间
     * @param object
     */
    public void post(Object object) {
        bus.onNext(object);
    }

    /**
     * 根据类型接收相应的事件
     * @param eventType
     * @param <T>
     * @return
     */
    public <T> Observable toObservable(Class<T> eventType) {
        return bus.ofType(eventType);
    }
}
