angular.module('cgPrompt',['ui.bootstrap']);

angular.module('cgPrompt').factory('prompt2',['$modal','$q',function($modal,$q){

    var prompt2 = function(options){
      var defer = $q.defer();
        var defaults = {
            title: '',
            message: '',
            developmessage:'',
            input: false,
            label: '',
            value: '',
            values: false,
            buttons: [
                {label:'Cancel',cancel:true},
                {label:'OK',primary:true}
            ]
        };

        if (options === undefined){
            options = {};
        }

        for (var key in defaults) {
            if (options[key] === undefined) {
                options[key] = defaults[key];
            }
        }



        $modal.open({
            templateUrl:'angular-prompt.html',
            controller: 'cgPromptCtrl',
            resolve: {
                options:function(){
                    return options;
                }
            }
        }).result.then(function(result){
            if (options.input){

                defer.resolve(result.input);
            } else {
                defer.resolve(result.button);
            }
        }, function(){
            defer.reject();
        });

        return defer.promise;
    };

    return prompt2;
	}
]);
angular.module('cgPrompt').directive('ngEnter', function () {
    return function (scope, element, attrs) {
        element.bind("keydown keypress", function (event) {
          console.log(event);
            if(event.which === 13) {
                scope.$apply(function (){
                    scope.$eval(attrs.ngEnter);
                });

                event.preventDefault();
            }
        });
    };
});

angular.module('cgPrompt').controller('cgPromptCtrl',['$scope','options','$timeout',function($scope,options,$timeout){

    $scope.input = {name:options.value};

    $scope.options = options;

    $scope.buttonClicked = function(button){

        if (button.cancel){
            $scope.$dismiss();
            return;
        }
        if (options.input && angular.element(document.querySelector('#cgPromptForm')).scope().cgPromptForm.$invalid){
            $scope.changed = true;
            return;
        }
        $scope.$close({button:button,input:$scope.input.name});
    };

    $scope.submit = function(){

        var ok;
        angular.forEach($scope.options.buttons,function(button){
            if (button.primary){
                ok = button;
            }
        });
        if (ok){
            $scope.buttonClicked(ok);
        }
        else{
            //enter event
            var button={};
            button.label="test";
            button.cancel=false;
            button.primary=false;
            $scope.$close({button:button,input:$scope.input.name});

        }
    };





    $timeout(function(){
        var elem = document.querySelector('#cgPromptInput');
        if (elem) {
            if (elem.select) {
                elem.select();
            }
            if (elem.focus) {
                elem.focus();
            }
        }
    },100);


}]);


angular.module('cgPrompt').run(['$templateCache', function($templateCache) {
  'use strict';

  $templateCache.put('angular-prompt.html',
    "<div>\n" +
    "    <div class=\"modal-header k-content\">\n" +
    "        <button type=\"button\" class=\"close pull-right\" ng-click=\"$dismiss()\" aria-hidden=\"true\">×</button>\n" +
    "        <h4 class=\"modal-title\">{{options.title}}</h4>\n" +
    "    </div>\n" +
    "    <div class=\"modal-body k-content\">\n" +
    "\n" +
    "        <p ng-if=\"options.message\">\n" +
    "            {{options.message}}\n" +
    "        </p>\n" +
    "\n" +
    "        <p ng-if=\"options.developmessage\">\n" +
    "            <font color=red>{{options.developmessage}}</font>\n" +
    "        </p>\n" +
    "\n" +
    "        <form id=\"cgPromptForm\" name=\"cgPromptForm\" ng-if=\"options.input\" ng-submit=\"submit()\">\n" +
    "            <div class=\"form-group\" ng-class=\"{'has-error':cgPromptForm.$invalid && changed}\">\n" +
    "                <label for=\"cgPromptInput\">{{options.label}}</label>\n" +
    "                <input id=\"cgPromptInput\" type=\"{{options.inputtype}}\" class=\"form-control\"  placeholder=\"{{options.label}}\" ng-model=\"input.name\" required ng-change=\"changed=true\" ng-if=\"!options.values || options.values.length === 0\"/ autofocus=\"autofocus\">\n" +
    "                <div class=\"input-group\" ng-if=\"options.values\">\n" +
    "                    <input id=\"cgPromptInput\" type=\"text\" class=\"form-control\" placeholder=\"{{options.label}}\" ng-model=\"input.name\" required ng-change=\"changed=true\" autofocus=\"autofocus\" ng-enter=\"submit2()\"/>\n" +
    "\n" +
    "                    <div class=\"input-group-btn\" dropdown>\n" +
    "                        <button type=\"button\" class=\"btn btn-default dropdown-toggle\" dropdown-toggle data-toggle=\"dropdown\"><span class=\"caret\"></span></button>\n" +
    "                        <ul class=\"dropdown-menu pull-right\">\n" +
    "                            <li ng-repeat=\"value in options.values\"><a href=\"\" ng-click=\"input.name = value\">{{value}}</a></li>\n" +
    "                        </ul>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "         </form>\n" +
    "\n" +
    "    </div>\n" +
    "    <div class=\"modal-footer k-content\">\n" +
    "        <button ng-repeat=\"button in options.buttons track by button.label\" class=\"btn btn-default {{button.class}}\" ng-class=\"{'btn-primary':button.primary}\" ng-click=\"buttonClicked(button)\">{{button.label}}</button>\n" +
    "    </div>\n" +
    "</div>"
  );

}]);
