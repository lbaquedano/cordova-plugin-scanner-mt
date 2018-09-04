
module.exports = {
    scan: function (name, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "scanner", "scan", [name]);
    }
};

//var exec = cordova.require("cordova/exec");

