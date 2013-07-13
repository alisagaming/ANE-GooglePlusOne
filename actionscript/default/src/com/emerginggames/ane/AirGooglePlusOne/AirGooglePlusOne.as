package com.emerginggames.ane.AirGooglePlusOne
{
	import flash.events.EventDispatcher;
	
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
			return false;
		}
		
		public function AirGooglePlusOne()
		{
			if (!_instance)
			{
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
		
		public function plusOneURL( title: String, url: String ) : void
		{
			return;
		}

		private static var _instance : AirGooglePlusOne;
	}
}