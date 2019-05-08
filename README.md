# RxJava
Exploring Reactive programming and RxJava API. Most of it is going through Baeldungs tutorial https://www.baeldung.com/rx-java</br>

Building blocks for RxJava</br>

Observables:</br>
	-Represents sources of data</br>
Subscribers</br>
	-Listening to the observables</br>
Set of methods for modifying and composing data. </br>

Observables</br>
	-Starts providing data once a subscriber starts listening. </br>
	-May emit any number of items (including 0)</br>
	-Can terminate successfully or with error</br>
	-Sources may never terminate </br>
		-Observable for a button click can potentially product an infinite stream of events. </br>

Subscribers</br>
	-Observable can have any number of subscribers</br>
	-If new item is emitted from observable - onNext method is called on each subscriber</br>
	-If observable finishes its data flow successfully:</br>
		-onComplete() method is called on each subscriber. </br>
	-If observable finishes with error</br>
		-onError() is called on each subscriber</br>
		
Reactive programming provides a simple way of asynchronous programming.</br>
Simplify asynchronously processing of potential long running operations. </br>
Defined way of handling multiple events, errors and termination of the event stream. </br>
Defined way: running different tasks in different threads. </br>

Possible to convert the stream before its received by the observers. </br>
Chain operations</br>
Reduces the need for state variables.</br>
