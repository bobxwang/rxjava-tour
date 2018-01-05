#### subject
> is an magic object,it can be an Observable, it also can be an Observer
* it has four son :
    * PublishSubject 一个基础子类
    * BehaviorSubject 向订阅者发送截至订阅前最新的一个数据对象(或初始值),然后正常发送订阅后的数据流
    * ReplaySubject 缓存它所订阅的所有数据，向任意一个订阅它的观察者重发
    * AsyncSubject 只会发布最后一个数据给已经订阅的每一个观察者

#### Schedulers

    * io --> 专用于I/O操作，不是Rx的默认方法
    * computation --> 计算工作默认的调度器，与I/O无关，是buffer,debounce,delay,interval,sample,skip默认
    * immediate --> 立即在当前线程执行工作，是timeout,timeInterval及timestamp默认
    * newThread
    * trampoline --> 当我们想在当前线程执行一个任务，但不是立即，可以用此将其入队，是repeat,retry方法默认调度器
    
#### others 

    /**
     * we can using Observable.empty, never, throw to constructor an Observable
     * defer -> 创建后不立即执行，有观察者订阅时才发射
     * timer -> 一段时间后再发射
     * sample(30,TimeUnit.SECONDS) -> 指定的时间间隔里发身最近一次数据
     * timeout() -> 限时，在指定时间间隔 Observable 不发射值的话，就会触发 onError()函数
     * debounce() -> 过滤发射速率过快的数据，即：在一个时间间隔过去之后，仍然没有发射的话，则发射最后的那个
     *
     * concatMap解决了flatMap的交叉问题
     */
