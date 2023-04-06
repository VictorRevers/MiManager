let reports = document.getElementById("reports");
            let create = document.getElementById("create");
            let list = document.getElementById("list");
            
            function changeView(param){
                let view = param;
                
                if(view === "reports"){
                    reports.removeAttribute("hidden");
                    create.setAttribute("hidden", true);
                    list.setAttribute("hidden", true);
                }
                
                if(view === "create"){
                    create.removeAttribute("hidden");
                    reports.setAttribute("hidden", true);
                    list.setAttribute("hidden", true);
                }
                
                if(view === "list"){
                    list.removeAttribute("hidden");
                    create.setAttribute("hidden", true);
                    reports.setAttribute("hidden", true);
                }
            }
