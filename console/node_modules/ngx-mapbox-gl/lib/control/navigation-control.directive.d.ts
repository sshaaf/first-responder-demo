import { AfterContentInit } from '@angular/core';
import { MapService } from '../map/map.service';
import { ControlComponent } from './control.component';
export declare class NavigationControlDirective implements AfterContentInit {
    private MapService;
    private ControlComponent;
    showCompass?: boolean;
    showZoom?: boolean;
    constructor(MapService: MapService, ControlComponent: ControlComponent);
    ngAfterContentInit(): void;
}
