函数响应式编程在java语言上的实现,参考[Reactive Extensions For .NET](https://github.com/Reactive-Extensions),异步处理数据流.不同于以往的**命令式程序**,它以数据流为核心,处理数据输入,处理及输出.是一个使用可观测的序列来组成异步,由数据进行程序的驱动

#### Subject
> is an magic object,it extend an Observable(订阅源) and implements an Observer(观察者)
* it has four son :
    * PublishSubject 一个基础子类
    * BehaviorSubject 向订阅者发送截至订阅前最新的一个数据对象(或初始值),然后正常发送订阅后的数据流
    * ReplaySubject 缓存它所订阅的所有数据，向任意一个订阅它的观察者重发
    * AsyncSubject 只会发布最后一个数据给已经订阅的每一个观察者
    * SerializedSubject 
    
#### Subscriber 
> 是一种特殊的观察者, 可以取消订阅被观察者, implements the Subscription(取消订阅)

#### Scheduler
> 调度器, is an object that schedules units of work, using **Schedulers** factory method
>
> subscribeOn: 指定subscribe所发生的线程,即Observable.OnSubscribe被激活时所处的线程,或者叫做事件产生的线程,只能调用一次,如果多次时只会第一个起作用,如果硬要多个,可使用**doOnSubscribe**来达到线程切换
>
> observeOn：指定Subscriber所运行在的线程,或者叫做事件消费的线程,可以多次调用 

    * io --> 专用于I/O操作，不是Rx的默认方法
    * computation --> 计算工作默认的调度器，与I/O无关，是buffer,debounce,delay,interval,sample,skip默认
    * immediate --> 立即在当前线程执行工作，是timeout,timeInterval及timestamp默认
    * newThread
    * trampoline --> 当我们想在当前线程执行一个任务，但不是立即，可以用此将其入队，是repeat,retry方法默认调度器
    
#### 线程模型
> onNext 是顺序执行的,不会同时由多个线程并发执行 
    
#### Others 

    /**
     * we can using Observable.empty, never, throw to constructor an Observable
     * defer -> 创建后不立即执行，有观察者订阅时才发射
     * timer -> 一段时间后再发射
     * sample(30,TimeUnit.SECONDS) -> 指定的时间间隔里发身最近一次数据
     * timeout() -> 限时，在指定时间间隔 Observable 不发射值的话，就会触发 onError()函数
     * debounce() -> 过滤发射速率过快的数据，即：在一个时间间隔过去之后，仍然没有发射的话，则发射最后的那个
     *
     * concatMap解决了flatMap的交叉问题(即传入的顺序跟出来的顺序可能不一致)
     */