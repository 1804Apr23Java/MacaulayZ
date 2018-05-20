import {RouterModule, Routes} from '@angular/router';
import { ModuleWithProviders } from '@angular/core/src/metadata/ng_module';
import {HomeComponent} from './home/home.component';
import {BesthorrorComponent} from './besthorror/besthorror.component';
import {SobaditsgoodComponent} from './sobaditsgood/sobaditsgood.component';
export const AppRoutes : Routes = [{path : 'app-home', component : HomeComponent}, {path : 'app-besthorror', component : BesthorrorComponent}, {path : 'app-sobaditsgood', component : SobaditsgoodComponent}];
export const ROUTING: ModuleWithProviders = RouterModule.forRoot(AppRoutes);
