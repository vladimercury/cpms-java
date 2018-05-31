sap.ui.define([
	"sap/ui/core/util/MockServer"
], function (MockServer) {
	"use strict";
	
	var fnParse = function(sBody) {
        var aItems = sBody.split("&");
        var oData = {};
        
        aItems.forEach(function(sItem) {
            var aPair = sItem.split("=");
            if (aPair.length === 2 && aPair[0]) {
                var sName = decodeURIComponent(aPair[0]).toLowerCase();
                if (/\[\]$/.test(sName)) {
                    sName = /(.*)\[\]$/.exec(sName)[1];
                    if (!oData[sName]) {
                        oData[sName] = [];
                    }
                    oData[sName].push(aPair[1]);
                } else {
                    oData[sName] = decodeURIComponent(aPair[1]);
                }
            }
        }); 
        return oData;
	};
	
	var aRequests = [
        {
            method: "GET",
            path: new RegExp("project[\\/]?"),
            response: function(oXhr, sUrlParams) {
                var oResponse = {
                    my: [{
                        "id": 1, "name": "CPMS", "description": "Corporate Project Management System",
                        "active": true, "priority": 1, "projectType": { "id": 1, "slug": "waterfall", "name": "Waterfall" }
                    }],
                    all: [{
                        "id": 2, "name": "Test creating", "description": "Descr", "active": false,
                        "priority": 2, "projectType": { "id": 1, "slug": "waterfall", "name": "Waterfall" }
                    }]
                };
                oXhr.respondJSON(200, {}, JSON.stringify(oResponse));
                return true;
            }
        },
        {
            method: "GET",
            path: /user[\/]?\??(.*)/,
            response: function(oXhr, sUrlParams) {
                var oBody = fnParse(sUrlParams || "");
                var oResponse = {};
                if (oBody.id === "0") {
                    oResponse = {
                        "id": 1, "login": "admin", "firstName": "Ivan", "lastName": "Ivanov",
                        "isAdmin": true,
                        "info": { "id": 1, "description": "CPMS Admin", "position": { "id": 1, "name": "Junior Software Developer"}},
                        "ownUser": false
                    };
                }
                oXhr.respondJSON(200, {}, JSON.stringify(oResponse));
                return true;
            }
        },
        {
            method: "GET",
            path: /message[\/]?\??(.*)/,
            response: function(oXhr, sUrlParams) {
                var oResponse = [{"id":2,"content":"Message to admin","creationDate":"27.05.2018 23:50:01","unread":false,"author":{"id":2,"login":"test","firstName":"Petr","lastName":"Petrov","isAdmin":true,"ownUser":false}}];
                oXhr.respondJSON(200, {}, JSON.stringify(oResponse));
                return true;
            }
        }
        
	    
	];
	
	return {

		init: function () {
			var oMockServer = new MockServer({
				rootUri: "./",
				requests: aRequests
			});

			var oUriParameters = jQuery.sap.getUriParameters();

			MockServer.config({
				autoRespond: true,
				autoRespondAfter: oUriParameters.get("serverDelay") || 100
			});
		
			oMockServer.start();
		}
	};

});