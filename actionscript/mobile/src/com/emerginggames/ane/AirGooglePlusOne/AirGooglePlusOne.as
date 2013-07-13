package com.emerginggames.ane.AirGooglePlusOne
{
	import flash.desktop.NativeApplication;
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.InvokeEvent;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	import flash.system.Capabilities;

	public class AirGooglePlusOne extends EventDispatcher
	{
		// --------------------------------------------------------------------------------------//
		//																						 //
		// 									   PUBLIC API										 //
		// 																						 //
		// --------------------------------------------------------------------------------------//

		public static const EVENT_PLUS_OK:String = "com.emerginggames.AirGooglePlusOne.EventPlusOk"
		
		public static function get isSupported() : Boolean
		{
			return true;
		}
		
		public function AirGooglePlusOne()
		{
			if (!_instance)
			{
				_context = ExtensionContext.createExtensionContext(EXTENSION_ID, null);
				if (!_context)
				{
					log("ERROR - Extension context is null. Please check if extension.xml is setup correctly.");
					return;
				}
				_context.addEventListener(StatusEvent.STATUS, onStatus)
				
				_instance = this;
			}
			else
			{
				throw Error("This is a singleton, use getInstance(), do not call the constructor directly.");
			}
		}
		
		public static function getInstance() : AirGooglePlusOne
		{
			return _instance ? _instance : new AirGooglePlusOne();
		}
		
		public function plusOneURL(title: String, url:String):void
		{
			_context.call('plusOneURL', title, url);
		}
		
		// --------------------------------------------------------------------------------------//
		//																						 //
		// 									 	PRIVATE API										 //
		// 																						 //
		// --------------------------------------------------------------------------------------//
		
		private static const EXTENSION_ID : String = "com.emerginggames.AirGooglePlusOne";
		
		private static var _instance : AirGooglePlusOne;
		
		private var _context : ExtensionContext;

		private function onStatus( event : StatusEvent ) : void
		{
			if (event.code == "GOOGLE_PLUS_ONE_OK") {
				dispatchEvent(new Event(EVENT_PLUS_OK));
			}
		}
		
		private function log( message : String ) : void
		{
			trace("[AirGooglePlusOne] " + message);
		}
	}
}